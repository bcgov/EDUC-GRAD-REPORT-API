package ca.bc.gov.educ.grad.report.api.service.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Component
public class XmlTransformer extends BaseTransformer {

    private static final Logger log = LoggerFactory.getLogger(XmlTransformer.class);

    static {
        OBJECT_MAPPER = new XmlMapper();
        OBJECT_MAPPER
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
