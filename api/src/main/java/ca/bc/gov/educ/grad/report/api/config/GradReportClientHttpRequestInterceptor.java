package ca.bc.gov.educ.grad.report.api.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradReportClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String CLASSNAME = GradReportClientHttpRequestInterceptor.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        logRequestDetails(request);
        return execution.execute(request, body);
    }
    private void logRequestDetails(HttpRequest request) {
        LOG.log(Level.INFO, "Headers: {}", request.getHeaders());
        LOG.log(Level.INFO, "Request Method: {}", request.getMethod());
        LOG.log(Level.INFO, "Request URI: {}", request.getURI());
    }
}
