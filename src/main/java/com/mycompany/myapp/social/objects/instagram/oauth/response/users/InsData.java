package com.mycompany.myapp.social.objects.instagram.oauth.response.users;

import com.google.gson.annotations.SerializedName;

public class InsData {

    @SerializedName("data")
    private UserSelf userSelf;

    @SerializedName("meta")
    private InsMeta meta;

    public InsData() {
    }

    public UserSelf getUserSelf() {
        return userSelf;
    }

    public InsMeta getMeta() {
        return meta;
    }

    @Override
    public String toString() {
        return "InsData{" +
            "userSelf=" + userSelf +
            ", meta=" + meta +
            '}';
    }
}
