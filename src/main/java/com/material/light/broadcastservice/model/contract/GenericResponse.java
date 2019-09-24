package com.material.light.broadcastservice.model.contract;

import com.google.gson.Gson;
import com.material.light.broadcastservice.model.enums.ResponseEnum;
import com.material.light.broadcastservice.model.exception.GenericException;
import lombok.Data;

@Data
public class GenericResponse {
    private String resultCode;
    private String resultNamespace;
    private String resultMessage;

    public GenericResponse(ResponseEnum responseEnum) {
        this.resultCode = responseEnum.getResultCode();
        this.resultNamespace = responseEnum.getResultNamespace();
        this.resultMessage = responseEnum.getResultMessage();
    }

    public GenericResponse(GenericException exception) {
        this.resultCode = exception.getResultCode();
        this.resultNamespace = exception.getResultNamespace();
        this.resultMessage = exception.getResultMessage();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
