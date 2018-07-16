package com.mycompany.myapp.social.objects.instagram.oauth.response.oauth;

import com.google.gson.annotations.SerializedName;
import com.mycompany.myapp.social.objects.instagram.oauth.response.User;

public class InsUserAuthResponse {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("user")
    private User user;

    public InsUserAuthResponse() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InsUserAuthResponse that = (InsUserAuthResponse) o;

        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = accessToken != null ? accessToken.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "InsUserAuthResponse{" +
            "accessToken='" + accessToken + '\'' +
            ", user=" + user +
            '}';
    }
}
