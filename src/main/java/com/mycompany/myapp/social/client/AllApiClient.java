package com.mycompany.myapp.social.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.myapp.social.actions.InstagramConnection;
import com.mycompany.myapp.social.actions.OdnoklassnikiConnection;
import org.apache.commons.lang3.StringUtils;

public class AllApiClient {
    private static final String API_VERSION = "1.0";

    private static final String VK_API_ADDRESS = "https://api.vk.com/method/";
    private static final String OK_API_ADDRESS = "https://api.odnoklassniki.com/";
    private static final String INS_API_ADDRESS = "https://api.instagram.com/";

    private static final String VK_OAUTH_ENDPOINT = "https://oauth.vk.com/";
    private static final String OK_OAUTH_ENDPOINT = "https://connect.ok.ru/oauth/";
    private static final String INS_OAUTH_ENDPOINT = "https://api.instagram.com/oauth/";

    private static final int DEFAULT_RETRY_ATTEMPTS_INTERNAL_SERVER_ERROR_COUNT = 3;
    private TransportClient transportClient;
    private Gson gson;

    private String vkApiEndpoint;
    private String okApiEndpoint;
    private String insApiEndpoint;

    private String vkOauthEndpoint;
    private String okOauthEndpoint;
    private String insOauthEndpoint;

    private int retryAttemptsInternalServerErrorCount;

    public AllApiClient(TransportClient transportClient) {
        this(transportClient, (new GsonBuilder()).create(), 3);
    }

    public AllApiClient(TransportClient transportClient, Gson gson, int retryAttemptsInternalServerErrorCount) {
        this.transportClient = transportClient;
        this.gson = gson;
        this.retryAttemptsInternalServerErrorCount = retryAttemptsInternalServerErrorCount;
        if (StringUtils.isNoneEmpty(new CharSequence[]{System.getProperty("api.host")})) {
            this.vkApiEndpoint = "https://" + System.getProperty("api.host") + "/method/";
        } else {
            this.vkApiEndpoint = "https://api.vk.com/method/";
            this.okApiEndpoint = "https://api.ok.ru/";
            this.insApiEndpoint = "https://api.instagram.com/";
        }

        if (StringUtils.isNoneEmpty(new CharSequence[]{System.getProperty("oauth.host")})) {
            this.vkOauthEndpoint = "https://" + System.getProperty("oauth.host") + "/";
        } else {
            this.vkOauthEndpoint = "https://oauth.vk.com/";
            this.okOauthEndpoint = "https://connect.ok.ru/oauth/";
            this.insOauthEndpoint = "https://api.instagram.com/oauth/";
        }
    }

    public TransportClient getTransportClient() {
        return this.transportClient;
    }

    public Gson getGson() {
        return this.gson;
    }

    int getRetryAttemptsInternalServerErrorCount() {
        return this.retryAttemptsInternalServerErrorCount;
    }

    public String getVkApiEndpoint() {
        return this.vkApiEndpoint;
    }

    public String getVkOAuthEndpoint() {
        return this.vkOauthEndpoint;
    }

    public String getOkApiEndpoint() {
        return this.okApiEndpoint;
    }

    public String getOkOAuthEndpoint() {
        return this.okOauthEndpoint;
    }

    public String getInsApiEndpoint() {
        return this.insApiEndpoint;
    }

    public String getInsOauthEndpoint() {
        return this.insOauthEndpoint;
    }

    public String getVersion(String providerId) {
        switch (providerId) {
            case "vkontakte":
                return "5.69";
            case "odnoklassniki":
                return "0";
            case "instagram":
                return "0";
            default:
                return "";
        }
    }

    /**
     * OdnoklassnikiConnection
     * */
    public OdnoklassnikiConnection getOkConnection() {
        return new OdnoklassnikiConnection(this);
    }

    /**
     * InstagramConnection
     * */
    public InstagramConnection getInsConnection() {
        return new InstagramConnection(this);
    }
}
