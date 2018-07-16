package com.mycompany.myapp.social.client;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.mycompany.myapp.social.exceptions.ApiException;
import com.mycompany.myapp.social.exceptions.ApiServerException;
import com.mycompany.myapp.social.exceptions.ClientException;
import com.mycompany.myapp.social.exceptions.ExceptionMapper;
import com.mycompany.myapp.social.objects.base.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class ApiRequest<T> {
    private static final Logger LOG = LoggerFactory.getLogger(ApiRequest.class);
    private TransportClient client;
    private Gson gson;
    private String url;
    private Type responseClass;
    private int retryAttempts;

    public ApiRequest(String url, TransportClient client, Gson gson, int retryAttempts, Type responseClass) {
        this.client = client;
        this.url = url;
        this.responseClass = responseClass;
        this.gson = gson;
        this.retryAttempts = retryAttempts;
    }

    protected String getUrl() {
        return this.url;
    }

    protected Gson getGson() {
        return this.gson;
    }

    protected TransportClient getClient() {
        return this.client;
    }

    protected Type getResponseClass() {
        return this.responseClass;
    }

    public T execute(String val) throws ApiException, ClientException {
        ApiServerException exception = null;
        int i = 0;

        while (i < this.retryAttempts) {
            try {
                return this.executeWithoutRetry(val);
            } catch (ApiServerException var4) {
                LOG.warn("API Server error", var4);
                exception = var4;
                ++i;
            }
        }

        throw exception;
    }

    private T executeWithoutRetry(String val) throws ClientException, ApiException {
        String textResponse = this.executeAsString(val);
        JsonReader jsonReader = new JsonReader(new StringReader(textResponse));
        JsonObject json = (JsonObject) (new JsonParser()).parse(jsonReader);
        if (json.has("error")) {
            JsonElement errorElement = json.get("error");

            Error error;
            try {
                error = (Error) this.gson.fromJson(errorElement, Error.class);
            } catch (JsonSyntaxException var7) {
                LOG.error("Invalid JSON: " + textResponse, var7);
                throw new ClientException("Can't parse json response");
            }

            ApiException exception = ExceptionMapper.parseException(error);
            LOG.error("API error", exception);
            throw exception;
        } else {
            JsonElement response = json;
            if (json.has("response")) {
                response = json.get("response");
            }

            try {
                return this.gson.fromJson((JsonElement) response, this.responseClass);
            } catch (JsonSyntaxException var8) {
                LOG.error("Invalid JSON: " + textResponse, var8);
                throw new ClientException("Can't parse json response");
            }
        }
    }

    public String executeAsString(String val) throws ClientException {
        ClientResponse response;
        try {
            if (val.equals("GET")) {
                response = this.client.get(this.url.concat("?").concat(this.getBody()));
            } else if (val.equals("DELETE")) {
                response = this.client.delete(this.url, this.getBody());
            } else {
                response = this.client.post(this.url, this.getBody());
            }
        } catch (IOException var4) {
            LOG.error("Problems with request: " + this.url, var4);
            throw new ClientException("I/O exception");
        }

        if (response.getStatusCode() != 200) {
            throw new ClientException("Internal API server error. Wrong status code: " + response.getStatusCode() + ". Content: " + response.getContent());
        } else {
            Map<String, String> headers = response.getHeaders();
            if (!headers.containsKey("Content-Type")) {
                throw new ClientException("No content type header");
            } else {
                String contentType = (String) headers.get("Content-Type");
                if (!contentType.contains("application/json") && !contentType.contains("text/javascript")) {
                    throw new ClientException("Invalid content type");
                } else {
                    return response.getContent();
                }
            }
        }
    }

    public ClientResponse executeAsRaw() throws ClientException {
        try {
            return this.client.post(this.url, this.getBody());
        } catch (IOException var2) {
            LOG.error("Problems with request: " + this.url, var2);
            throw new ClientException("I/O exception");
        }
    }

    protected abstract String getBody();
}
