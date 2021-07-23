package ca.bc.gov.educ.grad.controller;

import ca.bc.gov.educ.grad.dto.Message;
import ca.bc.gov.educ.grad.dto.Messages;
import ca.bc.gov.educ.grad.utils.AuditingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public abstract class BaseController {

    private static final String CLASS_NAME = BaseController.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    private HttpServletRequest httpServletRequest;

    protected void logRequest() {
        if(httpServletRequest.getUserPrincipal() != null)
            AuditingUtils.setCurrentUserId(httpServletRequest.getUserPrincipal().getName());
        if (log.isInfoEnabled()) {
            String method = this.httpServletRequest.getMethod();
            String path = this.httpServletRequest.getServletPath();
            String query = this.httpServletRequest.getQueryString();
            log.info(method + " " + path + (query == null ? "" : "?" + query));
        }
    }

    protected void logResponse(ResponseEntity response) {
        if (log.isInfoEnabled()) {

            int statusCode = response.getStatusCode().value();
            HttpStatus responseStatus = response.getStatusCode();
            String reasonPhrase = responseStatus.getReasonPhrase();
            log.info(statusCode + " " + reasonPhrase);

            HttpHeaders metaData = response.getHeaders();
            if (!metaData.isEmpty()) {
                log.info("ResponseEntity Metadata:");
                for (String key : metaData.keySet()) {
                    Object metaDatum = metaData.get(key);
                    log.info(key + " " + metaDatum);
                }
            }

            Object entity = response.getBody();
            if (entity != null && entity instanceof Messages) {
                Messages messages = (Messages) entity;
                log.info("Messages:");
                for (Message error : messages.getErrors()) {
                    log.info("path:" + error.getPath());
                    log.info("message:" + error.getMessage());
                    if (error.getMessageArguments() != null) {

                        log.info("messageArguments:" + Arrays.toString(error.getMessageArguments()));
                    }
                }
            }
        }
    }

    protected ResponseEntity getInternalServerErrorResponse(Throwable t) {
        ResponseEntity result = null;
        log.error(t.getMessage(), t);

        Throwable tmp = t;
        String message = null;
        if (tmp.getCause() != null) {
            tmp = tmp.getCause();
            message = tmp.getMessage();
        } else {
            message = tmp.getMessage();
        }
        if(message == null) {
            message = tmp.getClass().getName();
        }

        result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Messages(message));
        return result;
    }

    protected String nvl(String value, String defaultValue) {
        return (value == null) ? defaultValue : value;
    }

    protected final static String getCurrentUserId() {
        return AuditingUtils.getCurrentUserId();
    }

}

