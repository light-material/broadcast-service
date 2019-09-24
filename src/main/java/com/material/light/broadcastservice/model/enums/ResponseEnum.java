package com.material.light.broadcastservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {
    SUCCESS(200, "SUCCESS", "broadcast-service", "Success."),
    INVALID_PARAMETER(400, "INVALID_PARAMETER", "broadcast-service", "Invalid request parameters."),
    UNKNOWN_EXCEPTION(500, "UNKNOWN_EXCEPTION", "broadcast-service", "API failed due to unknown reason."),
    EMAIL_FAILED(500, "EMAIL_FAILED", "broadcast-service", "Unable to send email. Please try again.");

    private int statusCode;
    private String resultCode;
    private String resultNamespace;
    private String resultMessage;
}
