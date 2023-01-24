package ca.bc.gov.educ.grad.report.api.config;

import ca.bc.gov.educ.grad.report.api.util.JwtTokenUtil;
import ca.bc.gov.educ.grad.report.api.util.LogHelper;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import ca.bc.gov.educ.grad.report.utils.GradValidation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Component
public class RequestInterceptor implements AsyncHandlerInterceptor {

    @Autowired
    GradValidation validation;

    @Autowired
    EducGradReportApiConstants constants;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // for async this is called twice so need a check to avoid setting twice.
        if (request.getAttribute("startTime") == null) {
            final long startTime = Instant.now().toEpochMilli();
            request.setAttribute("startTime", startTime);
        }
        validation.clear();

        // correlationID
        val correlationID = request.getHeader(EducGradReportApiConstants.CORRELATION_ID);
        if (correlationID != null) {
            ReportRequestDataThreadLocal.setCorrelationID(correlationID);
        }

        // username
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) auth;
            Jwt jwt = (Jwt) authenticationToken.getCredentials();
            String username = JwtTokenUtil.getName(jwt);
            if (username != null) {
                ReportRequestDataThreadLocal.setCurrentUser(username);
            }
        }

        return true;
    }

    /**
     * After completion.
     *
     * @param request  the request
     * @param response the response
     * @param handler  the handler
     * @param ex       the ex
     */
    @Override
    public void afterCompletion(@NonNull final HttpServletRequest request, final HttpServletResponse response, @NonNull final Object handler, final Exception ex) {
        LogHelper.logServerHttpReqResponseDetails(request, response, constants.isSplunkLogHelperEnabled());
        val correlationID = request.getHeader(EducGradReportApiConstants.CORRELATION_ID);
        if (correlationID != null) {
            response.setHeader(EducGradReportApiConstants.CORRELATION_ID, request.getHeader(EducGradReportApiConstants.CORRELATION_ID));
        }
        // clear
        ReportRequestDataThreadLocal.clear();
    }
}
