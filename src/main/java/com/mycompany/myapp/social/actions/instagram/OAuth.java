package com.mycompany.myapp.social.actions.instagram;


import com.mycompany.myapp.social.client.AbstractAction;
import com.mycompany.myapp.social.client.AllApiClient;
import com.mycompany.myapp.social.queries.instagram.InsOAuthUserAuthorizationCodeFlowQuery;

public class OAuth extends AbstractAction {

    public OAuth(AllApiClient allApiClient, String providerId) {
        super(allApiClient, providerId);
    }

    public InsOAuthUserAuthorizationCodeFlowQuery userAuthorizationCodeFlow(String clientId, String clientSecret, String redirectUri, String code) {
        return new InsOAuthUserAuthorizationCodeFlowQuery(this.getClient(), this.getProviderId(), clientId, clientSecret, redirectUri, code);
    }

}
