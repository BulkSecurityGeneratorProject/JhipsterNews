package com.mycompany.myapp.social.client;

import java.util.Objects;

public class UserActor implements Actor {
    private Integer userId;
    private String accessToken;

    public UserActor(Integer userId, String accessToken) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public Integer getId() {
        return this.userId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            UserActor userActor = (UserActor)o;
            return Objects.equals(this.userId, userActor.userId) && Objects.equals(this.accessToken, userActor.accessToken);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.userId, this.accessToken});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UserActor{");
        sb.append("userId=").append(this.userId);
        sb.append(", accessToken='").append(this.accessToken).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
