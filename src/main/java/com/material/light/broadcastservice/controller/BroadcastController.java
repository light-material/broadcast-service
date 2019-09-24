package com.material.light.broadcastservice.controller;

import com.material.light.broadcastservice.model.contract.GenericResponse;
import com.material.light.broadcastservice.model.contract.SendEmail;
import com.material.light.broadcastservice.model.enums.ResponseEnum;
import com.material.light.broadcastservice.model.exception.GenericException;
import com.material.light.broadcastservice.service.email.EmailService;
import com.material.light.broadcastservice.service.validator.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class BroadcastController {

    private EmailService emailService;
    private ValidatorService validatorService;

    @Autowired
    public BroadcastController(EmailService emailService, ValidatorService validatorService) {
        this.emailService = emailService;
        this.validatorService = validatorService;
    }

    @PostMapping(value = "/send-email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> sendEmail(@RequestBody SendEmail.Request request) throws GenericException {
        log.info("Send Email Request {}", request);
        validatorService.beanValidate(request);
        emailService.sendEmail(request);

        return prepareResponse(ResponseEnum.SUCCESS);
    }

    private ResponseEntity<GenericResponse> prepareResponse(ResponseEnum responseEnum) {
        ResponseEntity<GenericResponse> response = ResponseEntity.status(responseEnum.getStatusCode())
                .body(new GenericResponse(responseEnum));
        log.info("Response: {}", response);
        return response;
    }
}
