package com.mycompany.myapp.social.actions;


import com.mycompany.myapp.social.actions.odnoklassniki.OkUsers;
import com.mycompany.myapp.social.client.AllApiClient;

public class OdnoklassnikiConnection {
    private AllApiClient allApiClient;

    public OdnoklassnikiConnection(AllApiClient allApiClient) {
        this.allApiClient = allApiClient;
    }

    public OkUsers getUsers(){
        return new OkUsers(this.allApiClient, "odnoklassniki");
    }
}
