package ca.bc.gov.educ.grad.controller;

import ca.bc.gov.educ.grad.utils.AuditingUtils;
import ca.bc.gov.educ.grad.utils.EducGradSignatureImageApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

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
            if(AuditingUtils.getSignatureImageUrl() == null) {
                Enumeration<String> requestHeaders = httpServletRequest.getHeaders("Authorization");
                String accessToken = null;
                String authorization = requestHeaders.hasMoreElements() ? requestHeaders.nextElement() : null;
                if(authorization != null) {
                    accessToken = StringUtils.substringAfter(authorization, "Bearer ");
                } else {
                    String[] parameterValues = httpServletRequest.getParameterValues("access_token");
                    if(parameterValues != null && parameterValues.length > 0) {
                        accessToken = parameterValues[0];
                    }
                }
                String protocol = StringUtils.startsWith(httpServletRequest.getProtocol(), "HTTP") ? "http://" : "https://";
                System.out.println("Protocol: " + httpServletRequest.getProtocol());
                String serverName = "localhost";
                try {
                    serverName = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("Unable to determine hostname for the request", e);
                }
                int port = httpServletRequest.getServerPort();
                String path = EducGradSignatureImageApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING + "/#signatureCode#";
                String method = httpServletRequest.getMethod();
                String accessTokenParam = accessToken == null ? "" : ("?access_token=" + accessToken);
                String signatureImageUrl = protocol + serverName + ":" + port + path + accessTokenParam;
                AuditingUtils.setSignatureImageUrl(signatureImageUrl);
                System.out.println(username + ": " + method + "->" + signatureImageUrl);
            }
        }
    }

    protected static String getCurrentUserId() {
        return AuditingUtils.getCurrentUserId();
    }

}

