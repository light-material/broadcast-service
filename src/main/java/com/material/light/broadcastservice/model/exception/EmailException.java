package com.material.light.broadcastservice.model.exception;

import com.material.light.broadcastservice.model.enums.ResponseEnum;

public class EmailException extends GenericException {
    public EmailException(ResponseEnum responseEnum) {
        super(responseEnum);
    }
}
