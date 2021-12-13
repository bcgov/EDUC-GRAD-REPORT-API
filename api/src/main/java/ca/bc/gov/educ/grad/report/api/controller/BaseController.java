package ca.bc.gov.educ.grad.report.api.controller;

import ca.bc.gov.educ.grad.report.utils.AuditingUtils;
import ca.bc.gov.educ.grad.report.utils.EducGradSignatureImageApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

public abstract class BaseController {

    private static final String CLASS_NAME = BaseController.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Value("${endpoint.educ-grad-report-api.get-signature-by-code.url}")
    String signatureImageUrlProperty;

    protected void logRequest() {
        HttpServletRequest httpServletRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        if (httpServletRequest != null) {
            String username = "";
            if (httpServletRequest.getUserPrincipal() != null) {
                username = httpServletRequest.getUserPrincipal().getName();
                AuditingUtils.setCurrentUserId(username);
            }

            Enumeration<String> requestHeaders = httpServletRequest.getHeaders("Authorization");
            String accessToken = null;
            String authorization = requestHeaders.hasMoreElements() ? requestHeaders.nextElement() : null;
            if (authorization != null) {
                accessToken = StringUtils.substringAfter(authorization, "Bearer ");
            } else {
                String[] parameterValues = httpServletRequest.getParameterValues("access_token");
                if (parameterValues != null && parameterValues.length > 0) {
                    accessToken = parameterValues[0];
                }
            }
            String signatureImageUrl = "";
            if (StringUtils.trimToNull(signatureImageUrlProperty) == null) {
                System.out.println("Signature URL Property not found");
                String protocol = StringUtils.startsWith(httpServletRequest.getProtocol(), "HTTP") ? "http://" : "https://";
                String serverName = "localhost";
                try {
                    serverName = InetAddress.getLocalHost().getCanonicalHostName();
                } catch (UnknownHostException e) {
                    log.error("Unable to determine hostname for the request", e);
                }
                int port = httpServletRequest.getServerPort();
                String path = EducGradSignatureImageApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING + "/#signatureCode#";
                String method = httpServletRequest.getMethod();
                String accessTokenParam = accessToken == null ? "" : ("?access_token=" + accessToken);
                signatureImageUrl = protocol + serverName + ":" + port + path + accessTokenParam;
                System.out.println(username + ": " + method + "->" + signatureImageUrl);
            } else {
                String accessTokenParam = accessToken == null ? "" : ("?access_token=" + accessToken);
                signatureImageUrl = signatureImageUrlProperty + "/#signatureCode#" + accessTokenParam;
                System.out.println(signatureImageUrl);
            }
            if (StringUtils.trimToNull(signatureImageUrl) == null) {
                throw new RuntimeException("signatureImageUrl is undefined");
            }
            AuditingUtils.setSignatureImageUrl(signatureImageUrl);
        } else {
            throw new RuntimeException("HttpServletRequest is not available for the REST call");
        }
    }

    protected static String getCurrentUserId() {
        return AuditingUtils.getCurrentUserId();
    }

}

