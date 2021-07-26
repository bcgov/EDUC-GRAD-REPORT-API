package ca.bc.gov.educ.grad.controller;

import ca.bc.gov.educ.grad.utils.AuditingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    private static final String CLASS_NAME = BaseController.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    protected void logRequest() {
        HttpServletRequest httpServletRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        if(httpServletRequest != null) {
            String username = "";
            if (httpServletRequest.getUserPrincipal() != null) {
                username = httpServletRequest.getUserPrincipal().getName();
                AuditingUtils.setCurrentUserId(username);
            }
            if (log.isInfoEnabled()) {
                String method = httpServletRequest.getMethod();
                String path = httpServletRequest.getServletPath();
                String query = httpServletRequest.getQueryString();
                log.info(username + ": " + method + " " + path + (query == null ? "" : "?" + query));
            }
        }
    }

    protected static String getCurrentUserId() {
        return AuditingUtils.getCurrentUserId();
    }

}

