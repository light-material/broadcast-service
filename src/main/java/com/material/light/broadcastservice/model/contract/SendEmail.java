package com.material.light.broadcastservice.model.contract;

import com.google.gson.Gson;
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

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
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
