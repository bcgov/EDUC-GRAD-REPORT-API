package ca.bc.gov.educ.grad.report.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@IntegrationComponentScan
@EnableIntegration
@ComponentScans({
        @ComponentScan("ca.bc.gov.educ.grad.report")
})
@EntityScan(basePackages = {"ca.bc.gov.educ.grad.report.entity"} )
@EnableJpaRepositories(basePackages = {"ca.bc.gov.educ.grad.report.dao"})
@PropertySource("classpath:messages.properties")
public class ReportApiApplicationConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
