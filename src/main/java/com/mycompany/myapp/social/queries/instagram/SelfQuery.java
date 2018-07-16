package com.mycompany.myapp.social.queries.instagram;

import com.mycompany.myapp.social.client.AbstractQueryBuilder;
import com.mycompany.myapp.social.client.AllApiClient;
import com.mycompany.myapp.social.objects.instagram.oauth.response.users.InsData;

import java.util.Collection;
import java.util.Collections;

public class SelfQuery extends AbstractQueryBuilder<SelfQuery, InsData> {

    public SelfQuery(AllApiClient client, String providerId) {
        super(client, providerId, "v1/users/self/", InsData.class);
    }

    public SelfQuery accessToken(String value) {
        return (SelfQuery) this.unsafeParam("access_token", value);
    }

    @Override
    protected SelfQuery getThis() {
        return this;
    }

    @Override
    protected Collection<String> essentialKeys() {
        return Collections.EMPTY_LIST;
    }
}
