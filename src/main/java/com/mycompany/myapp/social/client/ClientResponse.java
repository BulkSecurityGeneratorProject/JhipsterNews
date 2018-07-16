package com.mycompany.myapp.social.client;

import java.util.Map;

public class ClientResponse {
    private int statusCode;
    private String content;
    private Map<String, String> headers;

    public ClientResponse(int statusCode, String content, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.content = content;
        this.headers = headers;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getContent() {
        return this.content;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }
}
