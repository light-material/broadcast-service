package com.material.light.broadcastservice.model.exception;

import com.material.light.broadcastservice.model.enums.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenericException extends Exception {

    private int statusCode;
    private String resultCode;
    private String resultNamespace;
    private String resultMessage;

    GenericException(ResponseEnum responseEnum) {
        super(responseEnum.getResultMessage());
        this.statusCode = responseEnum.getStatusCode();
        this.resultCode = responseEnum.getResultCode();
        this.resultNamespace = responseEnum.getResultNamespace();
        this.resultMessage = responseEnum.getResultMessage();
    }

    GenericException(ResponseEnum responseEnum, String resultMessage) {
        super(responseEnum.getResultMessage());
        this.statusCode = responseEnum.getStatusCode();
        this.resultCode = responseEnum.getResultCode();
        this.resultNamespace = responseEnum.getResultNamespace();
        this.resultMessage = resultMessage;
    }
}
