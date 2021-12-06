package ca.bc.gov.educ.api.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@IntegrationComponentScan
@EnableIntegration
@ComponentScans({
        @ComponentScan("ca.bc.gov.educ.api.report"),
        @ComponentScan("ca.bc.gov.educ.grad")
})
public class ReportApiApplicationConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
