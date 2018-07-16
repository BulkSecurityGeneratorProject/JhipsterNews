package com.mycompany.myapp.social.actions.instagram;


import com.mycompany.myapp.social.client.AbstractAction;
import com.mycompany.myapp.social.client.AllApiClient;
import com.mycompany.myapp.social.queries.instagram.SelfQuery;

public class InsUsers extends AbstractAction {

    public InsUsers(AllApiClient allApiClient, String providerId) {
        super(allApiClient, providerId);
    }

    public SelfQuery selfQuery() {
        return new SelfQuery(this.getClient(), this.getProviderId());
    }
}
