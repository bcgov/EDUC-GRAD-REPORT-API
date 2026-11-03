package ca.bc.gov.educ.grad.report.api.config;

import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Caps the size of signature save requests before Jackson reads the body into memory.
 * The service's 1 MB image check only runs after the whole request has been deserialized.
 */
@Component
public class SignatureSaveRequestSizeFilter extends OncePerRequestFilter {

    // a 1 MB PNG is about 1.4 MB as base64, plus a few short JSON fields
    public static final long SIGNATURE_SAVE_REQUEST_MAX_BYTES = 2L * 1024 * 1024;
    private static final String SIGNATURE_SAVE_PATH = EducGradReportApiConstants.GRAD_SIGNATURE_IMAGE_API_ROOT_MAPPING + EducGradReportApiConstants.SAVE_SIGNATURE_IMAGE;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // the container decodes the servlet path, so an encoded URL can't skip the check
        return !("POST".equalsIgnoreCase(request.getMethod()) && SIGNATURE_SAVE_PATH.equals(request.getServletPath()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long requestBodyBytes = request.getContentLengthLong();
        if (requestBodyBytes < 0) {
            // a chunked body has no declared size to check up front
            rejectRequest(response, HttpServletResponse.SC_LENGTH_REQUIRED, "Content-Length is required");
            return;
        }
        if (requestBodyBytes > SIGNATURE_SAVE_REQUEST_MAX_BYTES) {
            rejectRequest(response, HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, "Signature save request is too large: maximum is 2 MB");
            return;
        }
        filterChain.doFilter(request, response);
    }

    // write the response directly; sendError would forward to /error, which needs authentication
    private static void rejectRequest(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("text/plain");
        response.getWriter().write(message);
    }
}
