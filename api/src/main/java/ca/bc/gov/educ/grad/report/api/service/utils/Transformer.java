package ca.bc.gov.educ.grad.report.api.service.utils;

import java.io.InputStream;
import java.io.Serializable;

public interface Transformer extends Serializable {

    public Object unmarshall(byte[] input, Class<?> clazz);

    public Object unmarshall(String input, Class<?> clazz);

    public Object unmarshall(InputStream input, Class<?> clazz);

    public String marshallPrettyPrinter(Object input);

    public String marshall(Object input);

    public String getAccept();

    public String getFormat();

    public String getContentType();
}
