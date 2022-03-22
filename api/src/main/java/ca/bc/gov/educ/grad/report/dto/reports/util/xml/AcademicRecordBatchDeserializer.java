package ca.bc.gov.educ.grad.report.dto.reports.util.xml;

import ca.bc.gov.educ.grad.report.dto.reports.xml.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

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
        JsonNode nodeTree = jp.getCodec().readTree(jp);
        JsonNode root = nodeTree.get("gradStudent");

        AcademicRecordBatch result = new AcademicRecordBatch();
        HighSchoolTranscript transcript = new HighSchoolTranscript();
        Student student = new Student();
        AcademicRecord academicRecord = new AcademicRecord();
        Person person = new Person();

        String firstName = nullSafeString(root.get("legalFirstName")).asText("");
        String lastName = nullSafeString(root.get("legalLastName")).asText("");
        person.setName(new Name(firstName, lastName));

        //set objects tree
        student.setAcademicRecord(academicRecord);
        student.setPerson(person);
        transcript.setStudent(student);
        result.setHighSchoolTranscript(transcript);

        return result;
    }

    private JsonNode nullSafeString(final JsonNode s) {
        return s == null ? new TextNode("") : s;
    }

    private JsonNode nullSafeInteger(final JsonNode s) {
        return s == null ? new IntNode(0) : s;
    }

    private JsonNode nullSafeBoolean(final JsonNode s) {
        return s == null ? BooleanNode.FALSE : s;
    }
}
