package com.mycompany.myapp.social.objects.base;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class RequestParam {
    @SerializedName("key")
    private String key;
    @SerializedName("value")
    private String value;

    public RequestParam() {
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.value, this.key});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            RequestParam requestParam = (RequestParam) o;
            return Objects.equals(this.key, requestParam.key) && Objects.equals(this.value, requestParam.value);
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("RequestParam{");
        sb.append("key='").append(this.key).append("'");
        sb.append(", value='").append(this.value).append("'");
        sb.append('}');
        return sb.toString();
    }
}
