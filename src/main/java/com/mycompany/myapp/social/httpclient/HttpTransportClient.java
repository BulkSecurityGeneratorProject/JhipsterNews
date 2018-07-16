package com.mycompany.myapp.social.httpclient;


import com.mycompany.myapp.social.client.ClientResponse;
import com.mycompany.myapp.social.client.TransportClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class HttpTransportClient implements TransportClient {
    private static final Logger LOG = LoggerFactory.getLogger(HttpTransportClient.class);
    private static final String ENCODING = "UTF-8";
    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String USER_AGENT = "Java VK SDK/0.5.12";
    private static final String EMPTY_PAYLOAD = "-";
    private static final int MAX_SIMULTANEOUS_CONNECTIONS = 300;
    private static final int DEFAULT_RETRY_ATTEMPTS_NETWORK_ERROR_COUNT = 3;
    private static final int DEFAULT_RETRY_INVALID_STATUS_COUNT = 3;
    private static final int FULL_CONNECTION_TIMEOUT_S = 60;
    private static final int CONNECTION_TIMEOUT_MS = 5000;
    private static final int SOCKET_TIMEOUT_MS = 60000;
    private static final ConnectionsSupervisor SUPERVISOR = new ConnectionsSupervisor();
    private static HttpTransportClient instance;
    private static HttpClient httpClient;
    private int retryAttemptsNetworkErrorCount;
    private int retryAttemptsInvalidStatusCount;

    public HttpTransportClient() {
        this(3, 3);
    }

    public HttpTransportClient(int retryAttemptsNetworkErrorCount, int retryAttemptsInvalidStatusCount) {
        this.retryAttemptsNetworkErrorCount = retryAttemptsNetworkErrorCount;
        this.retryAttemptsInvalidStatusCount = retryAttemptsInvalidStatusCount;
        CookieStore cookieStore = new BasicCookieStore();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).setCookieSpec("standard").build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(300);
        connectionManager.setDefaultMaxPerRoute(300);
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).setDefaultCookieStore(cookieStore).setUserAgent("Java VK SDK/0.5.12").build();
    }

    public static HttpTransportClient getInstance() {
        if (instance == null) {
            instance = new HttpTransportClient();
        }

        return instance;
    }

    private static Map<String, String> getHeaders(Header[] headers) {
        Map<String, String> result = new HashMap();
        Header[] var2 = headers;
        int var3 = headers.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Header header = var2[var4];
            result.put(header.getName(), header.getValue());
        }

        return result;
    }

    private ClientResponse callWithStatusCheck(HttpRequestBase request) throws IOException {
        int attempts = 0;

        ClientResponse response;
        do {
            response = this.call(request);
            ++attempts;
        } while(attempts < this.retryAttemptsInvalidStatusCount && this.isInvalidGatewayStatus(response.getStatusCode()));

        return response;
    }

    private boolean isInvalidGatewayStatus(int status) {
        return status == 502 || status == 504;
    }

    private ClientResponse call(HttpRequestBase request) throws IOException {
        SocketException exception = null;
        int i = 0;

        while(i < this.retryAttemptsNetworkErrorCount) {
            try {
                SUPERVISOR.addRequest(request);
                long startTime = System.currentTimeMillis();
                HttpResponse response = httpClient.execute(request);
                long endTime = System.currentTimeMillis();
                long resultTime = endTime - startTime;

                ClientResponse var16;
                try {
                    InputStream content = response.getEntity().getContent();
                    Throwable var12 = null;

                    try {
                        String result = IOUtils.toString(content, "UTF-8");
                        Map<String, String> responseHeaders = getHeaders(response.getAllHeaders());
                        Map<String, String> requestHeaders = getHeaders(request.getAllHeaders());
                        this.logRequest(request, requestHeaders, response, responseHeaders, result, resultTime);
                        var16 = new ClientResponse(response.getStatusLine().getStatusCode(), result, responseHeaders);
                    } catch (Throwable var34) {
                        var12 = var34;
                        throw var34;
                    } finally {
                        if (content != null) {
                            if (var12 != null) {
                                try {
                                    content.close();
                                } catch (Throwable var33) {
                                    var12.addSuppressed(var33);
                                }
                            } else {
                                content.close();
                            }
                        }

                    }
                } finally {
                    SUPERVISOR.removeRequest(request);
                }

                return var16;
            } catch (SocketException var37) {
                this.logRequest(request);
                LOG.warn("Network troubles", var37);
                exception = var37;
                ++i;
            }
        }

        throw exception;
    }

    private void logRequest(HttpRequestBase request) throws IOException {
        this.logRequest(request, (Map)null, (HttpResponse)null, (Map)null, (String)null, (Long)null);
    }

    private String getRequestPayload(HttpRequestBase request) throws IOException {
        if (!(request instanceof HttpPost)) {
            return "-";
        } else {
            HttpPost postRequest = (HttpPost)request;
            if (postRequest.getEntity() == null) {
                return "-";
            } else {
                if (StringUtils.isNotEmpty(postRequest.getEntity().getContentType().getValue())) {
                    String contentType = postRequest.getEntity().getContentType().getValue();
                    if (contentType.contains("multipart/form-data")) {
                        return "-";
                    }
                }

                return IOUtils.toString(postRequest.getEntity().getContent(), StandardCharsets.UTF_8);
            }
        }
    }

    private void logRequest(HttpRequestBase request, Map<String, String> requestHeaders, HttpResponse response, Map<String, String> responseHeaders, String body, Long time) throws IOException {
        if (LOG.isDebugEnabled()) {
            String payload = this.getRequestPayload(request);
            StringBuilder builder = (new StringBuilder("\n")).append("Request:\n").append("\t").append("Headers: ").append(requestHeaders != null ? requestHeaders : "-").append("\n").append("\t").append("Method: ").append(request.getMethod()).append("\n").append("\t").append("URI: ").append(request.getURI()).append("\n").append("\t").append("Payload: ").append(payload).append("\n").append("\t").append("Time: ").append(time != null ? time : "-").append("\n");
            if (response != null) {
                builder.append("Response:\n").append("\t").append("Status: ").append(response.getStatusLine().toString()).append("\n").append("\t").append("Headers: ").append(responseHeaders != null ? responseHeaders : "-").append("\n").append("\t").append("Body: ").append(body != null ? body : "-").append("\n");
            }

            LOG.debug(builder.toString());
        } else if (LOG.isInfoEnabled()) {
            StringBuilder builder = (new StringBuilder()).append("Request: ").append(request.getURI().toURL().toString());
            if (time != null) {
                builder.append("\t\t").append(time);
            }

            LOG.info(builder.toString());
        }

    }

    public ClientResponse get(String url) throws IOException {
        return this.get(url, "application/x-www-form-urlencoded");
    }

    public ClientResponse get(String url, String contentType) throws IOException {
        HttpGet request = new HttpGet(url);
        request.setHeader("Content-Type", contentType);
        return this.callWithStatusCheck(request);
    }

    public ClientResponse post(String url) throws IOException {
        return this.post(url, (String)null);
    }

    public ClientResponse post(String url, String body) throws IOException {
        return this.post(url, body, "application/x-www-form-urlencoded");
    }

    public ClientResponse post(String url, String body, String contentType) throws IOException {
        HttpPost request = new HttpPost(url);
        request.setHeader("Content-Type", contentType);
        if (body != null) {
            request.setEntity(new StringEntity(body, "UTF-8"));
        }

        return this.callWithStatusCheck(request);
    }

    public ClientResponse post(String url, String fileName, File file) throws IOException {
        HttpPost request = new HttpPost(url);
        FileBody fileBody = new FileBody(file);
        HttpEntity entity = MultipartEntityBuilder.create().addPart(fileName, fileBody).build();
        request.setEntity(entity);
        return this.callWithStatusCheck(request);
    }

    public ClientResponse delete(String url) throws IOException {
        return this.delete(url, (String)null, "application/x-www-form-urlencoded");
    }

    public ClientResponse delete(String url, String body) throws IOException {
        return this.delete(url, body, "application/x-www-form-urlencoded");
    }

    public ClientResponse delete(String url, String body, String contentType) throws IOException {
        HttpDeleteWithBody request = new HttpDeleteWithBody(url);
        request.setHeader("Content-Type", contentType);
        if (body != null) {
            request.setEntity(new StringEntity(body));
        }

        return this.callWithStatusCheck(request);
    }
}

