package com.mycompany.myapp.social.client;

public abstract class AbstractAction {
    private AllApiClient allApiClient;

    private String providerId;

    public AbstractAction(AllApiClient allApiClient, String providerId) {
        this.allApiClient = allApiClient;
        this.providerId = providerId;
    }

    protected AllApiClient getClient() {
        return allApiClient;
    }

    protected String getProviderId() {
        return providerId;
    }
}
