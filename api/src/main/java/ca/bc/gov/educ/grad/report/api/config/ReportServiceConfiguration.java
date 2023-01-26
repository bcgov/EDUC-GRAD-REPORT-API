package ca.bc.gov.educ.grad.report.api.config;

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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableAutoConfiguration
@EnableScheduling
@EntityScan(basePackages = {"ca.bc.gov.educ.grad.report.entity"} )
@EnableJpaRepositories(basePackages = {"ca.bc.gov.educ.grad.report.dao"})
public class ReportServiceConfiguration implements WebMvcConfigurer {

    RequestInterceptor requestInterceptor;

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
        return WebClient.builder().build();
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
}
