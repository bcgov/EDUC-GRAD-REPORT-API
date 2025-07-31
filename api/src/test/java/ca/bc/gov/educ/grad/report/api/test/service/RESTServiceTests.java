package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.service.RESTService;
import ca.bc.gov.educ.grad.report.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RESTServiceTests {

    @InjectMocks
    private RESTService restService;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restService = new RESTService(webClient);
    }

    @Test
    void testGet_withClass_returnsDummyResponse() {
        String testUrl = "http://dummy/api";
        DummyResponse expectedResponse = new DummyResponse("John", 25);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(DummyResponse.class))).thenReturn(Mono.just(expectedResponse));

        DummyResponse actual = restService.get(testUrl, DummyResponse.class, null);
        assertEquals(expectedResponse.name(), actual.name());
        assertEquals(expectedResponse.age(), actual.age());
    }

    @Test
    void testPost_withClass_returnsDummyResponse() {
        String testUrl = "http://dummy/api";
        DummyRequest dummyRequest = new DummyRequest("Alice", 30);
        DummyResponse expectedResponse = new DummyResponse("Success", 1);

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(DummyResponse.class))).thenReturn(Mono.just(expectedResponse));

        DummyResponse actual = restService.post(testUrl, dummyRequest, DummyResponse.class, null);
        assertEquals(expectedResponse.name(), actual.name());
    }

    @Test
    void testGet_withParameterizedTypeReference_returnsList() {
        String testUrl = "http://dummy/api/list";
        List<DummyResponse> expectedList = List.of(new DummyResponse("Anna", 22), new DummyResponse("Mark", 30));

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.just(expectedList));

        ParameterizedTypeReference<List<DummyResponse>> typeRef = new ParameterizedTypeReference<>() {};
        List<DummyResponse> actual = restService.get(testUrl, typeRef, null);
        assertEquals(expectedList.size(), actual.size());
        assertEquals(expectedList.get(0).name(), actual.get(0).name());
    }

    @Test
    void testGet_throwsServiceException_onServerError() {
        String testUrl = "http://dummy/api/error";

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenAnswer(invocation -> {
            Function<ClientResponse, Mono<? extends Throwable>> func = invocation.getArgument(1);
            return responseSpec;
        });
        when(responseSpec.bodyToMono(eq(DummyResponse.class)))
                .thenThrow(new WebClientResponseException("Internal Server Error", 500, "Internal", null, null, null));

        ServiceException thrown = assertThrows(ServiceException.class,
                () -> restService.get(testUrl, DummyResponse.class, null));
        assertEquals(500, thrown.getStatusCode());
        assertTrue(thrown.getMessage().contains(testUrl));
    }

    @Test
    void testPost_throwsServiceException_onNetworkFailure() {
        String testUrl = "http://dummy/api/post";
        DummyRequest dummyRequest = new DummyRequest("Test", 99);

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(DummyResponse.class))
                .thenThrow(new RuntimeException("Connection error"));

        ServiceException ex = assertThrows(ServiceException.class,
                () -> restService.post(testUrl, dummyRequest, DummyResponse.class, null));
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getStatusCode());
        assertTrue(ex.getMessage().contains("Connection error"));
    }

    @Test
    void testRetry_exhaustedThrowsServiceException() {
        String testUrl = "http://dummy/api/retry";

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(DummyResponse.class)))
                .thenThrow(new ServiceException("Temporary error", 503));

        ServiceException ex = assertThrows(ServiceException.class,
                () -> restService.get(testUrl, DummyResponse.class, null));
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getStatusCode());
        assertTrue(ex.getMessage().contains("retry"));
    }

    // ✅ Dummy request and response classes (records) used in test
    record DummyRequest(String name, int age) {}
    record DummyResponse(String name, int age) {}
}
