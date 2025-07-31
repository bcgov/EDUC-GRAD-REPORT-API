package ca.bc.gov.educ.grad.report.api.service;

import ca.bc.gov.educ.grad.report.api.util.TokenResponse;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TokenService {
    private final WebClient webClient;
    private final EducGradReportApiConstants constants;

    @Autowired
    public TokenService(@Qualifier("reportApiClient") WebClient webClient, EducGradReportApiConstants constants) {
        this.webClient = webClient;
        this.constants = constants;
    }

    public Mono<String> getAccessToken() {
        return webClient.post()
                .uri(constants.getTokenUrl())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", constants.getClientId(), constants.getClientSecret()))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .map(TokenResponse::getAccessToken);
    }
}
