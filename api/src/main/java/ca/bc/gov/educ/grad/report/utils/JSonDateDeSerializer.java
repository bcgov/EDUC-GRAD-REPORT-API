package ca.bc.gov.educ.grad.report.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alex.rybakov
 */
public class JSonDateDeSerializer extends JsonDeserializer<Date> {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String date = p.getText();
        if (date != null && !"null".equalsIgnoreCase(date)) {
            try {
                Date deserialzedDate = formatter.parse(date);
                return deserialzedDate;
            } catch (ParseException e) {
            }
        }
        return null;
    }
}

