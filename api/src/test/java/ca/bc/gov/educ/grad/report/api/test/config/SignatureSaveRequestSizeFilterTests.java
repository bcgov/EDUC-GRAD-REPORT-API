package ca.bc.gov.educ.grad.report.api.test.config;

import ca.bc.gov.educ.grad.report.api.config.SignatureSaveRequestSizeFilter;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SignatureSaveRequestSizeFilterTests {

    private static final String SIGNATURE_SAVE_PATH = "/api/v1/reports/signatures/save";

    private final SignatureSaveRequestSizeFilter signatureSaveRequestSizeFilter = new SignatureSaveRequestSizeFilter();

    @Test
    void testDoFilter_passesSaveRequestWithinLimit() throws Exception {
        MockFilterChain filterChain = new MockFilterChain();
        MockHttpServletResponse response = new MockHttpServletResponse();

        signatureSaveRequestSizeFilter.doFilter(request("POST", SIGNATURE_SAVE_PATH, new byte[1024]), response, filterChain);

        assertNotNull(filterChain.getRequest());
        assertEquals(200, response.getStatus());
    }

    @Test
    void testDoFilter_rejectsSaveRequestOverLimit() throws Exception {
        MockFilterChain filterChain = new MockFilterChain();
        MockHttpServletResponse response = new MockHttpServletResponse();
        byte[] oversizedBody = new byte[(int) SignatureSaveRequestSizeFilter.SIGNATURE_SAVE_REQUEST_MAX_BYTES + 1];

        signatureSaveRequestSizeFilter.doFilter(request("POST", SIGNATURE_SAVE_PATH, oversizedBody), response, filterChain);

        assertNull(filterChain.getRequest());
        assertEquals(413, response.getStatus());
        assertEquals("Signature save request is too large: maximum is 2 MB", response.getContentAsString());
    }

    @Test
    void testDoFilter_rejectsSaveRequestWithoutContentLength() throws Exception {
        MockFilterChain filterChain = new MockFilterChain();
        MockHttpServletResponse response = new MockHttpServletResponse();

        signatureSaveRequestSizeFilter.doFilter(request("POST", SIGNATURE_SAVE_PATH, null), response, filterChain);

        assertNull(filterChain.getRequest());
        assertEquals(411, response.getStatus());
    }

    @Test
    void testDoFilter_ignoresOtherSignatureRequests() throws Exception {
        MockFilterChain filterChain = new MockFilterChain();
        MockHttpServletResponse response = new MockHttpServletResponse();

        signatureSaveRequestSizeFilter.doFilter(request("GET", "/api/v1/reports/signatures/get/all", null), response, filterChain);

        assertNotNull(filterChain.getRequest());
        assertEquals(200, response.getStatus());
    }

    private static MockHttpServletRequest request(String method, String path, byte[] body) {
        MockHttpServletRequest request = new MockHttpServletRequest(method, path);
        request.setServletPath(path);
        if (body != null) {
            request.setContent(body);
        }
        return request;
    }
}
