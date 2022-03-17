package ca.bc.gov.educ.grad.report.api.service.utils;

import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;

@Component
abstract class BaseTransformer implements Transformer {

    private static final Logger log = LoggerFactory.getLogger(BaseTransformer.class);

    static ObjectMapper OBJECT_MAPPER;

    @Override
    public Object unmarshall(byte[] input, Class<?> clazz) throws TransformerException {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = OBJECT_MAPPER.readValue(input, clazz);
        } catch (IOException e) {
            throw new TransformerException(e);
        }
        log.info("Time taken for unmarshalling response from bytes to {} is {} ms", clazz.getName(), (System.currentTimeMillis() - start));
        return result;
    }

    public Object unmarshallWithWrapper(String input, Class<?> clazz) throws TransformerException {
        final ObjectReader reader = OBJECT_MAPPER.readerFor(clazz);
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = reader
                    .with(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    .with(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)
                    .readValue(input);
        } catch (IOException e) {
            throw new TransformerException(e);
        }
        log.info("Time taken for unmarshalling response from String to {} is {} ms", clazz.getSimpleName(), (System.currentTimeMillis() - start));
        return result;
    }

    public String marshallWithWrapper(Object input) throws TransformerException {
        ObjectWriter prettyPrinter = OBJECT_MAPPER.writer();//.writerWithDefaultPrettyPrinter();
        String result = null;
        try {
            result = prettyPrinter
                    .with(SerializationFeature.WRAP_ROOT_VALUE)
                    .writeValueAsString(input);
        } catch (IOException e) {
            throw new TransformerException(e);
        }

        return result;
    }

    @Override
    public Object unmarshall(String input, Class<?> clazz) throws TransformerException {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = OBJECT_MAPPER.readValue(input, clazz);
        } catch (IOException e) {
            throw new TransformerException(e);
        }
        log.info("Time taken for unmarshalling response from String to {} is {} ms", clazz.getName(), (System.currentTimeMillis() - start));
        return result;
    }

    @Override
    public Object unmarshall(InputStream input, Class<?> clazz) throws TransformerException {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = OBJECT_MAPPER.readValue(input, clazz);
        } catch (IOException e) {
            throw new TransformerException(e);
        }
        log.info("Time taken for unmarshalling response from stream to {} is {} ms", clazz.getName(), (System.currentTimeMillis() - start));
        return result;
    }

    @Override
    public String marshall(Object input) throws TransformerException {
        ObjectWriter prettyPrinter = OBJECT_MAPPER.writerWithDefaultPrettyPrinter();
        String result = null;
        try {
            result = prettyPrinter.writeValueAsString(input);
        } catch (IOException e) {
            throw new TransformerException(e);
        }

        return result;
    }

    @Override
    public abstract String getAccept();

    @Override
    public abstract String getFormat();

    @Override
    public abstract String getContentType();
}
