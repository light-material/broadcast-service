package com.material.light.broadcastservice.service.email;

import com.material.light.broadcastservice.config.properties.EmailProperty;
import com.material.light.broadcastservice.model.contract.SendEmail;
import com.material.light.broadcastservice.model.enums.ResponseEnum;
import com.material.light.broadcastservice.model.exception.EmailException;
import com.material.light.broadcastservice.model.exception.GenericException;
import com.material.light.broadcastservice.model.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Properties;

@Slf4j
@Service
public class EmailService {

    private EmailProperty emailProperty;

    @Autowired
    public EmailService(EmailProperty emailProperty) {
        this.emailProperty = emailProperty;
    }

    public void sendEmail(SendEmail.Request request) throws GenericException, UnsupportedEncodingException {
        log.info("Preparing to send email...");
        try {
            Properties properties = getProperties();
            decodeCredentials(request);
            Session session = getSession(properties, request.getCredentials());
            Message message = prepareMessage(session, request);
            Transport.send(message);
            log.info("Email sent.");
        } catch (GenericException e) {
            throw e;
        } catch (MessagingException e) {
            log.error("Failed to send email. ");
            throw new EmailException(ResponseEnum.EMAIL_FAILED);
        }
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", emailProperty.getAuth());
        properties.put("mail.smtp.starttls.enable", emailProperty.getStartTlsEnable());
        properties.put("mail.smtp.host", emailProperty.getHost());
        properties.put("mail.smtp.port", emailProperty.getPort());
        return properties;
    }

    private Session getSession(Properties properties, SendEmail.Credentials credentials) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(credentials.getUsername(), credentials.getPassword());
            }
        });
    }

    private Message prepareMessage(Session session, SendEmail.Request request) throws UnsupportedEncodingException, MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(request.getCredentials().getUsername(),
                request.getCredentials().getAlias()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(request.getRecipient()));
        message.setSubject(request.getSubject());
        message.setContent(request.getContent(), request.getContentType());

        return message;
    }

    private void decodeCredentials(SendEmail.Request request) throws InvalidParameterException {
        try {
            request.getCredentials().setUsername(new String(
                    Base64.getDecoder().decode(request.getCredentials().getUsername())));
            request.getCredentials().setPassword(new String(
                    Base64.getDecoder().decode(request.getCredentials().getPassword())));
            request.getCredentials().setAlias(new String(
                    Base64.getDecoder().decode(request.getCredentials().getAlias())));
        } catch (RuntimeException e) {
            throw new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, "Invalid Credentials.");
        }

    }
}
