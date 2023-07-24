package ca.bc.gov.educ.grad.report.api.service.utils;

import ca.bc.gov.educ.grad.report.utils.GradLocalDateDeserializer;
import ca.bc.gov.educ.grad.report.utils.GradLocalDateSerializer;
import ca.bc.gov.educ.grad.report.utils.GradLocalDateTimeDeserializer;
import ca.bc.gov.educ.grad.report.utils.GradLocalDateTimeSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
public class JsonTransformer extends BaseTransformer {

    @PostConstruct
    void initMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, new GradLocalDateSerializer());
        simpleModule.addSerializer(LocalDateTime.class, new GradLocalDateTimeSerializer());
        simpleModule.addDeserializer(LocalDate.class, new GradLocalDateDeserializer());
        simpleModule.addDeserializer(LocalDateTime.class, new GradLocalDateTimeDeserializer());
        objectMapper
                .findAndRegisterModules()
                .registerModule(simpleModule)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.INDENT_OUTPUT)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .enable(JsonGenerator.Feature.ESCAPE_NON_ASCII)
                .setTimeZone(TimeZone.getDefault())
        //        .enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS)
        ;
    }

    @Override
    public String getAccept() {
        return "application/json";
    }

    @Override
    public String getFormat() {
        return "json";
    }

    @Override
    public String getContentType() {
        return "application/json";
    }
}
