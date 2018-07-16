package com.mycompany.myapp.social.client;

import com.mycompany.myapp.social.queries.EnumParam;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractQueryBuilder<T, R> extends ApiRequest<R> {
    private final Map<String, String> params = new HashMap();
    private String method;

    public AbstractQueryBuilder(AllApiClient client, String providerId, String method, Type type) {
        super(getApiPoint(client, providerId) + method, client.getTransportClient(), client.getGson(), client.getRetryAttemptsInternalServerErrorCount(), type);
        this.method = method;
        if (providerId.equals("vkontakte")) {
            this.version(client.getVersion(providerId));
        }
    }

    public AbstractQueryBuilder(AllApiClient client, String providerId, String endpoint, String method, Type type) {
        super(endpoint + method, client.getTransportClient(), client.getGson(), client.getRetryAttemptsInternalServerErrorCount(), type);
        if (providerId.equals("vkontakte")) {
            this.version(client.getVersion(providerId));
        }
    }

    private static String getApiPoint(AllApiClient client, String providerId) {
        if (providerId.equals("odnoklassniki")) {
            return client.getOkApiEndpoint();
        } else if (providerId.equals("vkontakte")) {
            return client.getVkApiEndpoint();
        } else if (providerId.equals("instagram")) {
            return client.getInsApiEndpoint();
        } else {
            return "????????????";
        }
    }

    private static String boolAsParam(boolean param) {
        return param ? "1" : "0";
    }

    private static String mapToGetString(Map<String, String> params) {
        return (String) params.entrySet().stream().map((entry) -> {
            return (String) entry.getKey() + "=" + (entry.getValue() != null ? escape((String) entry.getValue()) : "");
        }).collect(Collectors.joining("&"));
    }

    private static String escape(String data) {
        try {
            return URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException(var2);
        }
    }

    protected T accessToken(String value) {
        return this.unsafeParam("access_token", value);
    }

    protected T clientSecret(String value) {
        return this.unsafeParam("client_secret", value);
    }

    public T lang(Lang value) {
        return this.unsafeParam("lang", value.getValue());
    }

    protected T version(String value) {
        return this.unsafeParam("v", value);
    }

    public T captchaSid(String value) {
        return this.unsafeParam("captcha_sid", value);
    }

    public T captchaKey(String value) {
        return this.unsafeParam("captcha_key", value);
    }

    public T confirm(Boolean value) {
        return this.unsafeParam("confirm", value.booleanValue());
    }

    public T unsafeParam(String key, String value) {
        this.params.put(key, value);
        return this.getThis();
    }

    public T unsafeParam(String key, int value) {
        return this.unsafeParam(key, Integer.toString(value));
    }

    public T unsafeParam(String key, boolean value) {
        return this.unsafeParam(key, boolAsParam(value));
    }

    public T unsafeParam(String key, Collection<?> value) {
        return this.unsafeParam(key, (String) value.stream().map(Objects::toString).collect(Collectors.joining(",")));
    }

    public <U> T unsafeParam(String key, U... value) {
        return this.unsafeParam(key, (Collection) Arrays.asList(value));
    }

    public T unsafeParam(String key, int[] value) {
        return this.unsafeParam(key, (String) IntStream.of(value).mapToObj(Integer::toString).collect(Collectors.joining(",")));
    }

    public T unsafeParam(String key, double value) {
        return this.unsafeParam(key, Double.toString(value));
    }

    public T unsafeParam(String key, float value) {
        return this.unsafeParam(key, Float.toString(value));
    }

    public T unsafeParam(String key, EnumParam value) {
        return this.unsafeParam(key, value.getValue());
    }

    public T unsafeParam(String key, EnumParam... fields) {
        return this.unsafeParam(key, (String) Arrays.stream(fields).map(EnumParam::getValue).collect(Collectors.joining(",")));
    }

    public T unsafeParam(String key, List<? extends EnumParam> fields) {
        return this.unsafeParam(key, (String) fields.stream().map(EnumParam::getValue).collect(Collectors.joining(",")));
    }

    protected String getBody() {
        return mapToGetString(this.build());
    }

    protected abstract T getThis();

    protected abstract Collection<String> essentialKeys();

    public Map<String, String> build() {
        if (!this.params.keySet().containsAll(this.essentialKeys())) {
            throw new IllegalArgumentException("Not all the keys are passed: essential keys are " + this.essentialKeys());
        } else {
            return Collections.unmodifiableMap(this.params);
        }
    }

    public String getMethod() {
        return this.method;
    }
}
