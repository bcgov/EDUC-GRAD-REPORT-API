package ca.bc.gov.educ.grad.report.dto.reports.util.xml;

import ca.bc.gov.educ.grad.report.dto.reports.xml.AcademicRecordBatch;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class AcademicRecordBatchDeserializer extends StdDeserializer<AcademicRecordBatch> {

    public AcademicRecordBatchDeserializer() {
        this(null);
    }

    public AcademicRecordBatchDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AcademicRecordBatch deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        return new AcademicRecordBatch();
    }

}
