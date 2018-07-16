package com.mycompany.myapp.social.httpclient;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public HttpDeleteWithBody(String uri) {
        this(URI.create(uri));
    }

    public HttpDeleteWithBody(URI uri) {
        this.setURI(uri);
    }

    public String getMethod() {
        return "DELETE";
    }
}
