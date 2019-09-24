package com.material.light.broadcastservice.model.contract;

import com.google.gson.Gson;
import com.material.light.broadcastservice.model.enums.ResponseEnum;
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

    public GenericResponse(String resultCode, String resultNamespace, String resultMessage) {
        this.resultCode = resultCode;
        this.resultNamespace = resultNamespace;
        this.resultMessage = resultMessage;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
