package ca.bc.gov.educ.grad.report.api.controller;

import ca.bc.gov.educ.grad.report.api.client.Pen;
import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.Student;
import ca.bc.gov.educ.grad.report.api.config.GradReportSignatureUser;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.report.api.util.JwtTokenUtil;
import ca.bc.gov.educ.grad.report.utils.AuditingUtils;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

public abstract class BaseController {

    private static final String CLASS_NAME = BaseController.class.getName();
    private static Logger log = LoggerFactory.getLogger(CLASS_NAME);

    @Value("${endpoint.educ-grad-report-api.get-signature-by-code.url}")
    String signatureImageUrlProperty;

    @Autowired
    JsonTransformer jsonTransformer;

    @SneakyThrows
    protected void logRequest(Object request) {
        HttpServletRequest httpServletRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        StringBuffer requestURL = httpServletRequest.getRequestURL();
        if (httpServletRequest.getQueryString() != null) {
            requestURL.append("?").append(httpServletRequest.getQueryString());
        }
        String completeURL = requestURL.toString();
        log.debug(completeURL);

        String appLogLevel = Optional.ofNullable(System.getenv("APP_LOG_LEVEL")).orElse("INFO");
        if("DEBUG".equalsIgnoreCase(appLogLevel)) {
            if (request instanceof ReportRequest) {
                ReportRequest cloneRequest = SerializationUtils.clone((ReportRequest)request);
                Student st = cloneRequest.getData().getStudent();
                hideStudentDataForDebug(st);
                if(cloneRequest.getData().getSchool() != null) {
                    for (Student s : cloneRequest.getData().getSchool().getStudents()) {
                        hideStudentDataForDebug(s);
                    }
                }
                String jsonRequest = jsonTransformer.marshall(cloneRequest);
                log.debug(jsonRequest);
            } else {
                String jsonRequest = jsonTransformer.marshall(request);
                log.debug(jsonRequest);
            }
        }

        String username = "";
        if (httpServletRequest.getUserPrincipal() != null) {
            username = httpServletRequest.getUserPrincipal().getName();
            AuditingUtils.setCurrentUserId(username);
        }

        String contentPath = httpServletRequest.getRequestURI();
        if(StringUtils.contains(contentPath, EducGradReportApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING)) {
            return;
        }

        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();
        String tokenKey = generator.generate(256);
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(tokenKey);
        String accessToken = tokenKey + jwtTokenUtil.generateToken(new GradReportSignatureUser(username));
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
            String path = EducGradReportApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING + "/#signatureCode#";
            String method = httpServletRequest.getMethod();
            String accessTokenParam = "?access_token=" + accessToken;
            signatureImageUrl = protocol + serverName + ":" + port + path + accessTokenParam;
            System.out.println(username + ": " + method + "->" + signatureImageUrl);
        } else {
            String accessTokenParam = "?access_token=" + accessToken;
            signatureImageUrl = signatureImageUrlProperty + "/#signatureCode#" + accessTokenParam;
            System.out.println(signatureImageUrl);
        }
        if (StringUtils.trimToNull(signatureImageUrl) == null) {
            throw new RuntimeException("signatureImageUrl is undefined");
        }
        AuditingUtils.setSignatureImageUrl(signatureImageUrl);

    }

    protected static String getCurrentUserId() {
        return AuditingUtils.getCurrentUserId();
    }

    private void hideStudentDataForDebug(Student st) {
        if (st != null) {
            Pen pen = ObjectUtils.defaultIfNull(st.getPen(), new Pen());
            pen.setPen(null);
            st.setPen(pen);
            st.setFirstName("John");
            st.setMiddleName("");
            st.setLastName("Doe");
        }
    }

}

