package ca.bc.gov.educ.grad.report.api.config;

import ca.bc.gov.educ.grad.report.api.util.LogHelper;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@SuppressWarnings("ALL")
@Configuration
@EnableAutoConfiguration
@EnableScheduling
@EntityScan(basePackages = {"ca.bc.gov.educ.grad.report.entity"} )
@EnableJpaRepositories(basePackages = {"ca.bc.gov.educ.grad.report.dao"})
public class ReportServiceConfiguration implements WebMvcConfigurer {

    RequestInterceptor requestInterceptor;
    LogHelper logHelper;
    EducGradReportApiConstants constants;

    @Autowired
    public ReportServiceConfiguration(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Bean
    public WebClient webClient() {
        HttpClient client = HttpClient.create();
        client.warmup().block();
        return WebClient.builder()
                .filter(setRequestHeaders())
                .filter(this.log())
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(new GradReportRestTemplateCustomizer());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
    }

    private ExchangeFilterFunction setRequestHeaders() {
        return (clientRequest, next) -> {
            ClientRequest modifiedRequest = ClientRequest.from(clientRequest)
                    .header(EducGradReportApiConstants.CORRELATION_ID, ReportRequestDataThreadLocal.getCorrelationID())
                    .header(EducGradReportApiConstants.USER_NAME, ReportRequestDataThreadLocal.getCurrentUser())
                    .header(EducGradReportApiConstants.REQUEST_SOURCE, EducGradReportApiConstants.API_NAME)
                    .build();
            return next.exchange(modifiedRequest);
        };
    }

    private ExchangeFilterFunction log() {
        return (clientRequest, next) -> next
                .exchange(clientRequest)
                .doOnNext((clientResponse -> logHelper.logClientHttpReqResponseDetails(
                        clientRequest.method(),
                        clientRequest.url().toString(),
                        clientResponse.statusCode().value(),
                        clientRequest.headers().get(EducGradReportApiConstants.CORRELATION_ID),
                        clientRequest.headers().get(EducGradReportApiConstants.REQUEST_SOURCE),
                        constants.isSplunkLogHelperEnabled())
                ));
    }

}
