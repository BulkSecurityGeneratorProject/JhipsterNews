package com.mycompany.myapp.social.actions.odnoklassniki;


import com.mycompany.myapp.social.client.AbstractAction;
import com.mycompany.myapp.social.client.AllApiClient;
import com.mycompany.myapp.social.client.UserActor;
import com.mycompany.myapp.social.queries.odnoklassniki.UsersGetCurrentUser;

public class OkUsers extends AbstractAction {

    public OkUsers(AllApiClient allApiClient, String providerId) {
        super(allApiClient, providerId);
    }

    public UsersGetCurrentUser getCallsLeftQuery(UserActor actor) {
        return new UsersGetCurrentUser(this.getClient(), actor, this.getProviderId());
    }
}
