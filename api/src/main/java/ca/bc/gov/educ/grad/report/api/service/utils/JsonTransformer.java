package ca.bc.gov.educ.grad.report.api.service.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class JsonTransformer extends BaseTransformer {

    @PostConstruct
    void initMapper() {
        if(objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper
                    .findAndRegisterModules()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .disable(SerializationFeature.INDENT_OUTPUT)
                    .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                    .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                    .enable(JsonGenerator.Feature.ESCAPE_NON_ASCII)
                    .setDateFormat(new SimpleDateFormat("yyyy-MM-dd h:mm:ss"))
                    .setTimeZone(TimeZone.getDefault())
            //        .enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS)
            ;
        }
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
