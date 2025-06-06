package ca.bc.gov.educ.grad.report.api.util;

import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class LogHelper {

    JsonTransformer jsonTransformer;

    private static final String EXCEPTION = "Exception ";

    @Autowired
    public LogHelper(JsonTransformer jsonTransformer) {
        this.jsonTransformer = jsonTransformer;
    }

    public void logServerHttpReqResponseDetails(@NonNull final HttpServletRequest request, final HttpServletResponse response, final boolean logging) {
        if (!logging) return;
        String appLogLevel = Optional.ofNullable(System.getenv("APP_LOG_LEVEL")).orElse("INFO");
        boolean isDebugMode = "DEBUG".equalsIgnoreCase(appLogLevel);
        try {
            final int status = response.getStatus();
            val totalTime = Instant.now().toEpochMilli() - (Long) request.getAttribute("startTime");
            final Map<String, Object> httpMap = new HashMap<>();
            httpMap.put("server_http_response_code", status);
            httpMap.put("server_http_request_method", request.getMethod());
            httpMap.put("server_http_query_params", request.getQueryString());
            val correlationID = request.getHeader(EducGradReportApiConstants.CORRELATION_ID);
            if (correlationID != null) {
                httpMap.put("correlation_id", correlationID);
            }
            val requestSource = request.getHeader(EducGradReportApiConstants.REQUEST_SOURCE);
            if (requestSource != null) {
                httpMap.put("request_source", requestSource);
            }
            httpMap.put("server_http_request_url", String.valueOf(request.getRequestURL()));
            httpMap.put("server_http_request_processing_time_ms", totalTime);
            if(isDebugMode) {
                httpMap.put("server_http_request_payload", getTruncatedPayload(request));
            }
            httpMap.put("server_http_request_remote_address", request.getRemoteAddr());
            httpMap.put("server_http_request_client_name", StringUtils.trimToEmpty(request.getHeader("X-Client-Name")));
            httpMap.put("server_http_request_user_name", ReportRequestDataThreadLocal.getCurrentUser());
            MDC.putCloseable("httpEvent", jsonTransformer.marshall(httpMap));
            if(isDebugMode) log.debug(""); else log.info("");
            MDC.clear();
        } catch (final Exception exception) {
            log.error(EXCEPTION, exception);
        }
    }

    /**
     * Truncating payload to 1000 characters due to logging constraints.
     * @param request * @return a String representing the truncated payload
     **/
    private static String getTruncatedPayload(final HttpServletRequest request) {
        String payload = String.valueOf(request.getAttribute("payload") != null ? request.getAttribute("payload") : "");
        return (payload.length() > 1000) ? payload.substring(0, 1000).concat("...") : payload;
    }


    /**
     * WebClient to call other REST APIs
     *
     * @param method
     * @param url
     * @param responseCode
     * @param correlationID
     */
    public void logClientHttpReqResponseDetails(@NonNull final HttpMethod method, final String url, final int responseCode, final List<String> correlationID,
                                                final List<String> requestSource, final boolean logging) {
        if (!logging) return;
        try {
            final Map<String, Object> httpMap = new HashMap<>();
            httpMap.put("client_http_response_code", responseCode);
            httpMap.put("client_http_request_method", method.toString());
            httpMap.put("client_http_request_url", url);
            if (correlationID != null) {
                httpMap.put("correlation_id", String.join(",", correlationID));
            }
            if (requestSource != null) {
                httpMap.put("request_source", String.join(",", requestSource));
            }
            MDC.putCloseable("httpEvent", jsonTransformer.marshall(httpMap));
            log.info("");
            MDC.clear();
        } catch (final Exception exception) {
            log.error(EXCEPTION, exception);
        }
    }

    /**
     * NATS messaging
     * the event is a json string.
     *
     * @param event the json string
     */
    public void logMessagingEventDetails(final String event, final boolean logging) {
        if (!logging) return;
        try {
            MDC.putCloseable("messageEvent", event);
            log.debug("");
            MDC.clear();
        } catch (final Exception exception) {
            log.error(EXCEPTION, exception);
        }
    }

    /**
     * log message details
     * the message is a string.
     *
     * @param message string
     */
    public void logMessage(final String message, final boolean logging) {
        if (!logging) return;
        try {
            MDC.putCloseable("msg", message);
            log.info("");
            MDC.clear();
        } catch (final Exception exception) {
            log.error(EXCEPTION, exception);
        }
    }
}
