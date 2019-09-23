package com.material.light.broadcastservice.model.contract;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface SendEmail {

    @EqualsAndHashCode(callSuper = true)
    @Data
    class Request extends BaseRequest {

        @NotNull
        @Valid
        private Credentials credentials;

        @NotBlank
        private String recipient;

        @NotBlank
        private String subject;

        @NotBlank
        private String content;

        @NotBlank
        private String contentType;
    }

    @Data
    class Credentials {
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String alias;
    }
}
