package com.mycompany.myapp.social.queries.odnoklassniki;

import com.mycompany.myapp.social.client.AbstractQueryBuilder;
import com.mycompany.myapp.social.client.AllApiClient;
import com.mycompany.myapp.social.client.UserActor;
import com.mycompany.myapp.social.objects.odnoklassniki.users.response.GetCurrentUser;

import java.util.Collections;
import java.util.List;

//@Slf4j
public class UsersGetCurrentUser extends AbstractQueryBuilder<UsersGetCurrentUser, GetCurrentUser> {

    public UsersGetCurrentUser(AllApiClient client, UserActor actor, String providerId) {
        super(client, providerId, "fb.do", GetCurrentUser.class);
        this.accessToken(actor.getAccessToken());
    }

    public UsersGetCurrentUser getApplicationKey(String value) {
        return (UsersGetCurrentUser) this.unsafeParam("application_key", value);
    }

    public UsersGetCurrentUser getFormat(String value) {
        return (UsersGetCurrentUser) this.unsafeParam("format", value);
    }

    public UsersGetCurrentUser getMethod(String value) {
        return (UsersGetCurrentUser) this.unsafeParam("method", value);
    }

    public UsersGetCurrentUser getSig(String value) {
        return (UsersGetCurrentUser) this.unsafeParam("sig", value);
    }

    @Override
    protected UsersGetCurrentUser getThis() {
        return this;
    }

    protected List<String> essentialKeys() {
        return Collections.EMPTY_LIST;
    }
}
