package ca.bc.gov.educ.grad.report.api.controller;

import ca.bc.gov.educ.grad.report.api.config.GradReportSignatureUser;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.report.api.util.JwtTokenUtil;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.exception.InvalidParameterException;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import ca.bc.gov.educ.grad.report.utils.GradValidation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class BaseController {

    private static final String CLASS_NAME = BaseController.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Autowired
    GradValidation validation;

    @Value("${endpoint.educ-grad-report-api.get-signature-by-code.url}")
    String signatureImageUrlProperty;

    @Autowired
    JsonTransformer jsonTransformer;

    @SneakyThrows
    protected void logRequest(Object request) {
        HttpServletRequest httpServletRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        StringBuilder requestURL = new StringBuilder(httpServletRequest.getRequestURL().toString());
        if (httpServletRequest.getQueryString() != null) {
            requestURL.append("?").append(httpServletRequest.getQueryString());
        }
        String completeURL = requestURL.toString();
        log.debug(completeURL);

        String contentPath = httpServletRequest.getRequestURI();
        if(StringUtils.contains(contentPath, EducGradReportApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING)) {
            return;
        }

        String username = ReportRequestDataThreadLocal.getCurrentUser();
        assert StringUtils.isNotBlank(username);

        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();
        String tokenKey = generator.generate(256);
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(tokenKey);
        String accessToken = tokenKey + jwtTokenUtil.generateToken(new GradReportSignatureUser(username));
        String signatureImageUrl = "";
        if (StringUtils.trimToNull(signatureImageUrlProperty) == null) {
            String protocol = StringUtils.startsWith(httpServletRequest.getProtocol(), "HTTP") ? "http://" : "https://";
            String serverName = "localhost";
            try {
                serverName = InetAddress.getLocalHost().getCanonicalHostName();
            } catch (UnknownHostException e) {
                log.error("Unable to determine hostname for the request", e);
            }
            int port = httpServletRequest.getServerPort();
            String path = EducGradReportApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING + "/#signatureCode#";
            String method = httpServletRequest.getMethod();
            String accessTokenParam = "?access_token=" + accessToken;
            signatureImageUrl = protocol + serverName + ":" + port + path + accessTokenParam;
            log.debug("{}: {}->{}", username, method, signatureImageUrl);
        } else {
            String accessTokenParam = "?access_token=" + accessToken;
            signatureImageUrl = signatureImageUrlProperty + "/#signatureCode#" + accessTokenParam;
            log.debug(signatureImageUrl);
        }
        if (StringUtils.trimToNull(signatureImageUrl) == null) {
            throw new InvalidParameterException("signatureImageUrl is undefined");
        }
        ReportRequestDataThreadLocal.setSignatureImageUrl(signatureImageUrl);

    }

    protected ResponseEntity<byte[]> getInternalServerErrorResponse(Throwable t) {
        ResponseEntity<byte[]> result = null;

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        StringBuilder sb = new StringBuilder();
        if(validation.hasErrors()) {
            for(String error: validation.getErrors()) {
                sb.append(error).append('\n');
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        Throwable tmp = t;
        String message = sb.toString();
        if (tmp.getCause() != null) {
            tmp = tmp.getCause();
        }
        if(StringUtils.isBlank(message)) {
            message = tmp.getMessage();
        }
        if(StringUtils.isBlank(message)) {
            message = tmp.getClass().getName();
        }

        result = ResponseEntity.status(httpStatus).body(message.getBytes());
        return result;
    }
    
    protected ResponseEntity<byte[]> handleBinaryResponse(byte[] resultBinary, String reportFile) {
        return handleBinaryResponse(resultBinary, reportFile, MediaType.APPLICATION_PDF);
    }

    protected ResponseEntity<byte[]> handleBinaryResponse(byte[] resultBinary, String reportFile, MediaType contentType) {
        ResponseEntity<byte[]> response = null;

        StringBuilder sb = new StringBuilder();
        if(validation.hasErrors()) {
            for(String error: validation.getErrors()) {
                sb.append(error).append('\n');
            }
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            if (resultBinary.length > 0) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "inline; filename=" + reportFile);
                response = ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(contentType)
                        .body(resultBinary);
            } else {
                response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return response;
    }

}

