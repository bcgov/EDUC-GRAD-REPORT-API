package ca.bc.gov.educ.grad.report.api.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@IntegrationComponentScan
@EnableIntegration
@ComponentScans({
        @ComponentScan("ca.bc.gov.educ.grad.report")
})
@PropertySource("classpath:messages.properties")
public class ReportApiApplicationConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(false);
        configurer.setIgnoreResourceNotFound(false);
        return configurer;
    }
}
