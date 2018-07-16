package com.mycompany.myapp.social.objects.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Error {
    @SerializedName("error_code")
    private Integer errorCode;
    @SerializedName("error_msg")
    private String errorMsg;
    @SerializedName("captcha_sid")
    private String captchaSid;
    @SerializedName("captcha_img")
    private String captchaImg;
    @SerializedName("confirmation_text")
    private String confirmationText;
    @SerializedName("redirect_uri")
    private String redirectUri;
    @SerializedName("request_params")
    private List<RequestParam> requestParams;

    public Error() {
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public String getCaptchaImg() {
        return this.captchaImg;
    }

    public String getCaptchaSid() {
        return this.captchaSid;
    }

    public String getConfirmationText() {
        return this.confirmationText;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public List<RequestParam> getRequestParams() {
        return this.requestParams;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.errorCode, this.requestParams, this.errorMsg});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Error error = (Error)o;
            return Objects.equals(this.errorCode, error.errorCode) && Objects.equals(this.errorMsg, error.errorMsg) && Objects.equals(this.requestParams, error.requestParams);
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Error{");
        sb.append("errorCode=").append(this.errorCode);
        sb.append(", errorMsg='").append(this.errorMsg).append("'");
        sb.append(", captchaSid='").append(this.captchaSid).append("'");
        sb.append(", captchaImg='").append(this.captchaImg).append("'");
        sb.append(", confirmationText='").append(this.confirmationText).append("'");
        sb.append(", redirectUri='").append(this.redirectUri).append("'");
        sb.append(", requestParams=").append(this.requestParams);
        sb.append('}');
        return sb.toString();
    }
}
