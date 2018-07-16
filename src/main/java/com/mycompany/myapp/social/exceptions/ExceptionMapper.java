package com.mycompany.myapp.social.exceptions;

import com.mycompany.myapp.social.objects.base.Error;

public class ExceptionMapper {
    public ExceptionMapper() {
    }

    public static ApiException parseException(Error error) {
        switch (error.getErrorCode().intValue()) {
            case 10:
                return new ApiServerException(error.getErrorMsg());
            default:
                return new ApiException(error.getErrorCode(), error.getErrorMsg());
        }
    }
}
