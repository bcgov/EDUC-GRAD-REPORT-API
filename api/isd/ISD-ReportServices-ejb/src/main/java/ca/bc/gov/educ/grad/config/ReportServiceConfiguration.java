package ca.bc.gov.educ.grad.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableAutoConfiguration
@ComponentScans({
        @ComponentScan("ca.bc.gov.educ.isd"),
        @ComponentScan("ca.bc.gov.educ.grad")
})
@EntityScan(basePackages = {"ca.bc.gov.educ.grad.entity"} )
@EnableJpaRepositories(basePackages = {"ca.bc.gov.educ.grad.dao"})
@PropertySource("classpath:messages.properties")
public class ReportServiceConfiguration {

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
}
