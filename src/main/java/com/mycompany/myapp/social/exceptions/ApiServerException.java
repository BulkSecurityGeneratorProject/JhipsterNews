package com.mycompany.myapp.social.exceptions;

public class ApiServerException extends ApiException {
    public ApiServerException(String message) {
        super(Integer.valueOf(10), "Internal server error", message);
    }
}
