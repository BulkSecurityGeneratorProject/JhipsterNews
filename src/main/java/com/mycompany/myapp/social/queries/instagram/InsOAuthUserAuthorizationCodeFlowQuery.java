package com.mycompany.myapp.social.queries.instagram;

import com.mycompany.myapp.social.client.AbstractQueryBuilder;
import com.mycompany.myapp.social.client.AllApiClient;
import com.mycompany.myapp.social.objects.instagram.oauth.response.oauth.InsUserAuthResponse;

import java.util.Arrays;
import java.util.Collection;

public class InsOAuthUserAuthorizationCodeFlowQuery extends AbstractQueryBuilder<InsOAuthUserAuthorizationCodeFlowQuery, InsUserAuthResponse> {

    public InsOAuthUserAuthorizationCodeFlowQuery(AllApiClient client, String providerId, String clientId, String clientSecret, String redirectUri, String code) {
        super(client, providerId, "oauth/access_token", InsUserAuthResponse.class);
        this.clientId(clientId);
        this.clientSecret(clientSecret);
        this.grantType("authorization_code");
        this.redirectUri(redirectUri);
        this.code(code);
    }

    public InsOAuthUserAuthorizationCodeFlowQuery clientId(String value) {
        return (InsOAuthUserAuthorizationCodeFlowQuery) this.unsafeParam("client_id", value);
    }

    public InsOAuthUserAuthorizationCodeFlowQuery clientSecret(String value) {
        return (InsOAuthUserAuthorizationCodeFlowQuery) this.unsafeParam("client_secret", value);
    }

    public InsOAuthUserAuthorizationCodeFlowQuery grantType(String value) {
        return (InsOAuthUserAuthorizationCodeFlowQuery) this.unsafeParam("grant_type", value);
    }

    public InsOAuthUserAuthorizationCodeFlowQuery redirectUri(String value) {
        return (InsOAuthUserAuthorizationCodeFlowQuery) this.unsafeParam("redirect_uri", value);
    }

    public InsOAuthUserAuthorizationCodeFlowQuery code(String value) {
        return (InsOAuthUserAuthorizationCodeFlowQuery) this.unsafeParam("code", value);
    }

    protected InsOAuthUserAuthorizationCodeFlowQuery getThis() {
        return this;
    }

    protected Collection<String> essentialKeys() {
        return Arrays.asList("client_id", "client_secret", "grant_type", "redirect_uri", "code");
    }
}
