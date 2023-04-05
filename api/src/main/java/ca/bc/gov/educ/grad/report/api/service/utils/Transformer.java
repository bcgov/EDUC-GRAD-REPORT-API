package ca.bc.gov.educ.grad.report.api.service.utils;

import javax.xml.transform.TransformerException;
import java.io.InputStream;
import java.io.Serializable;

public interface Transformer extends Serializable {

    public Object unmarshall(byte[] input, Class<?> clazz) throws TransformerException;

    public Object unmarshall(String input, Class<?> clazz) throws TransformerException;

    public Object unmarshall(InputStream input, Class<?> clazz) throws TransformerException;

    public String marshallPrettyPrinter(Object input) throws TransformerException;

    public String marshall(Object input);

    public String getAccept();

    public String getFormat();

    public String getContentType();
}
