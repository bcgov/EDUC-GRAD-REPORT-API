package ca.bc.gov.educ.grad.report.api.service.utils;

import ca.bc.gov.educ.grad.report.exception.ReportApiServiceException;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

abstract class BaseTransformer implements Transformer {

    private static final Logger log = LoggerFactory.getLogger(BaseTransformer.class);

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Object unmarshall(byte[] input, Class<?> clazz) {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = objectMapper.readValue(input, clazz);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        log.debug("Time taken for unmarshalling response from bytes to {} is {} ms", clazz.getName(), (System.currentTimeMillis() - start));
        return result;
    }

    public Object unmarshallWithWrapper(String input, Class<?> clazz) {
        final ObjectReader reader = objectMapper.readerFor(clazz);
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = reader
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .with(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)
                    .readValue(input);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        log.debug("Time taken for unmarshalling response from String to {} is {} ms", clazz.getSimpleName(), (System.currentTimeMillis() - start));
        return result;
    }

    public String marshallWithWrapper(Object input) {
        ObjectWriter prettyPrinter = objectMapper.writer();
        String result = null;
        try {
            result = prettyPrinter
                    .with(SerializationFeature.WRAP_ROOT_VALUE)
                    .writeValueAsString(input);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return result;
    }

    @Override
    public Object unmarshall(String input, Class<?> clazz) {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = objectMapper.readValue(input, clazz);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        log.debug("Time taken for unmarshalling response from String to {} is {} ms", clazz.getName(), (System.currentTimeMillis() - start));
        return result;
    }

    @Override
    public Object unmarshall(InputStream input, Class<?> clazz) {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = objectMapper.readValue(input, clazz);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        log.debug("Time taken for unmarshalling response from stream to {} is {} ms", clazz.getName(), (System.currentTimeMillis() - start));
        return result;
    }

    @Override
    public String marshall(Object input) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(input);
        } catch (IOException e) {
            throw new ReportApiServiceException(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public String marshallPrettyPrinter(Object input) {
        ObjectWriter prettyPrinter = objectMapper.writerWithDefaultPrettyPrinter();
        String result = null;
        try {
            result = prettyPrinter.writeValueAsString(input);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }

        return result;
    }

}
