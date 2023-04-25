package ca.bc.gov.educ.grad.report.api.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.TimeZone;

import static ca.bc.gov.educ.grad.report.api.util.ReportApiConstants.DATETIME_FORMAT;

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

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        TimeZone defaultTimezone = TimeZone.getDefault();
        String timeZoneId = Optional.ofNullable(System.getenv("TZ")).orElse(defaultTimezone.getID());
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.serializers(localDateTimeSerializer).timeZone(TimeZone.getTimeZone(timeZoneId));
    }
}
