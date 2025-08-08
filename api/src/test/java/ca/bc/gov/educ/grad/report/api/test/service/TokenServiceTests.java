package ca.bc.gov.educ.grad.report.api.test.service;

import ca.bc.gov.educ.grad.report.api.service.TokenService;
import ca.bc.gov.educ.grad.report.api.util.TokenResponse;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceTests {

    @Mock
    private EducGradReportApiConstants constants;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    @SuppressWarnings("rawtypes")
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private TokenService tokenService;

    @BeforeEach
    void setup() {
        tokenService = new TokenService(webClient, constants);
    }

    @Test
    void getAccessToken_shouldReturnTokenSuccessfully() {
        // Arrange
        String tokenUrl = "https://auth.example.com/token";
        String clientId = "client123";
        String clientSecret = "secret456";
        String accessToken = "abc123token";

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);

        when(constants.getTokenUrl()).thenReturn(tokenUrl);
        when(constants.getClientId()).thenReturn(clientId);
        when(constants.getClientSecret()).thenReturn(clientSecret);

        // Mock WebClient chaining
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(tokenUrl)).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(MediaType.APPLICATION_FORM_URLENCODED)).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue("grant_type=client_credentials&client_id=client123&client_secret=secret456")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TokenResponse.class)).thenReturn(Mono.just(tokenResponse));

        // Act
        String result = tokenService.getAccessToken().block();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(accessToken, result);
    }

    @Test
    void getAccessToken_shouldHandleEmptyResponse() {
        // Arrange
        when(constants.getTokenUrl()).thenReturn("https://token.url");
        when(constants.getClientId()).thenReturn("id");
        when(constants.getClientSecret()).thenReturn("secret");

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TokenResponse.class)).thenReturn(Mono.empty());

        // Act
        String result = tokenService.getAccessToken().block();

        // Assert
        assertNull(result); // Since Mono.empty() returns null on block()
    }

    @Test
    void getAccessToken_shouldThrowException_onWebClientError() {
        // Arrange
        when(constants.getTokenUrl()).thenReturn("https://token.url");
        when(constants.getClientId()).thenReturn("id");
        when(constants.getClientSecret()).thenReturn("secret");

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.contentType(any())).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TokenResponse.class)).thenReturn(Mono.error(new RuntimeException("Service down")));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> tokenService.getAccessToken().block());
        Assertions.assertEquals("Service down", ex.getMessage());
    }
}
