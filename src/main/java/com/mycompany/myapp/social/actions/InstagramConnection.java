package com.mycompany.myapp.social.actions;


import com.mycompany.myapp.social.actions.instagram.InsUsers;
import com.mycompany.myapp.social.actions.instagram.OAuth;
import com.mycompany.myapp.social.client.AllApiClient;

public class InstagramConnection {

    private static final String PROVIDER_ID = "instagram";

    private AllApiClient allApiClient;

    public InstagramConnection(AllApiClient allApiClient) {
        this.allApiClient = allApiClient;
    }

    public OAuth getOAuth() {
        return new OAuth(this.allApiClient, PROVIDER_ID);
    }

    public InsUsers getUsers() {
       return new InsUsers(this.allApiClient, PROVIDER_ID);
    }
}
