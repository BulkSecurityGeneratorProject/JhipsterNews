package com.mycompany.myapp.social.objects.instagram.oauth.response.users;

import com.google.gson.annotations.SerializedName;

public class InsMeta {

    @SerializedName("code")
    private String code;

    public InsMeta(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InsMeta insMeta = (InsMeta) o;

        return getCode() != null ? getCode().equals(insMeta.getCode()) : insMeta.getCode() == null;
    }

    @Override
    public int hashCode() {
        return getCode() != null ? getCode().hashCode() : 0;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "InsMeta{" +
            "code='" + code + '\'' +
            '}';
    }
}
