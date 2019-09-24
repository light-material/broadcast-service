package com.material.light.broadcastservice.model.exception;

import com.material.light.broadcastservice.model.enums.ResponseEnum;

public class InvalidParameterException extends GenericException {

    public InvalidParameterException(ResponseEnum responseEnum, String resultMessage) {
        super(responseEnum, resultMessage);
    }
}
