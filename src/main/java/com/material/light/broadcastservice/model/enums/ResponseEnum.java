package com.material.light.broadcastservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {
    SUCCESS("SUCCESS", "broadcast-service", "Success."),
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "broadcast-service", "API failed due to unknown reason."),
    INVALID_PARAMETER("INVALID_PARAMETER", "broadcast-service", "Invalid request parameters."),
    EMAIL_SENDING_FAILED("EMAIL_SENDING_FAILED", "broadcast-service", "Unable to send email.");

    private String resultCode;
    private String resultNamespace;
    private String resultMessage;
}
