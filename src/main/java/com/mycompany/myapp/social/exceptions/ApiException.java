package com.mycompany.myapp.social.exceptions;

public class ApiException extends Exception {
    private String description;
    private String message;
    private Integer code;

    public ApiException(Integer code, String description, String message) {
        this.description = description;
        this.code = code;
        this.message = message;
    }

    public ApiException(Integer code, String message) {
        this(code, "Unknown", message);
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.description + " (" + this.code + "): " + this.message;
    }
}
