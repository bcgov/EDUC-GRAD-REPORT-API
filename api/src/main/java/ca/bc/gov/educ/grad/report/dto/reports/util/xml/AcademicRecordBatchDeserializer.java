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

        //Person
        Person person = new Person();

        String firstName = nullSafeString(root.get("legalFirstName")).asText("");
        String lastName = nullSafeString(root.get("legalLastName")).asText("");
        person.setName(new Name(firstName, lastName));

        String agencyAssignedID = nullSafeString(root.get("pen")).asText("");
        person.setAgencyIdentifier(new AgencyIdentifier(
                agencyAssignedID,
                "State",
                "BC"
        ));

        String birthDate = nullSafeString(root.get("dob")).asText("");
        person.setBirth(new Birth(birthDate));

        String gender = nullSafeString(root.get("genderCode")).asText("");
        person.setGender(new Gender(gender));

        String deceased = root.get("deceasedDate") == null ? "false" : "true";
        person.setDeceased(new Deceased(deceased));

        AcademicRecord academicRecord = new AcademicRecord();

        //School
        JsonNode schoolNode = nodeTree.get("school");

        School school = new School();

        String localOrganizationIDCode = nullSafeString(schoolNode.get("minCode")).asText("");
        String organizationName = nullSafeString(schoolNode.get("schoolName")).asText("");
        String localOrganizationIDQualifier = nullSafeString(schoolNode.get("provCode")).asText("");

        String addressLine = nullSafeString(schoolNode.get("address1")).asText("");
        String city = nullSafeString(schoolNode.get("city")).asText("");
        String stateProvinceCode = nullSafeString(schoolNode.get("provCode")).asText("");
        String postalCode = nullSafeString(schoolNode.get("postal")).asText("");

        school.setOrganizationName(organizationName);
        school.setLocalOrganizationID(new LocalOrganizationID(localOrganizationIDCode, localOrganizationIDQualifier));
        school.setContacts(new Contacts(new Address(
                addressLine,
                city,
                stateProvinceCode,
                postalCode
            ))
        );

        academicRecord.setSchool(school);

        String studentLevelCode = nullSafeString(root.get("studentGrade")).asText("");
        academicRecord.setStudentLevel(new StudentLevel(studentLevelCode));

        AcademicAward academicAward = new AcademicAward();

        String academicAwardLevel = nullSafeString(root.get("gradeCode")).asText("");
        academicAward.setAcademicAwardLevel(academicAwardLevel);

        JsonNode gradStatusNode = nodeTree.get("gradStatus");
        String academicCompletionDate = nullSafeString(gradStatusNode.get("programCompletionDate")).asText("");
        academicAward.setAcademicCompletionDate(academicCompletionDate);
        String academicAwardTitle = nullSafeString(gradStatusNode.get("program")).asText("");
        academicAward.setAcademicAwardTitle(academicAwardTitle);
        String honorsTitle = nullSafeString(gradStatusNode.get("honoursStanding")).asText("");
        academicAward.setAcademicHonors(new AcademicHonors(honorsTitle));
        String academicCompletionIndicator = nullSafeString(root.get("graduated")).asText("");
        academicAward.setAcademicCompletionIndicator(academicCompletionIndicator);
        Integer creditHoursEarned = nullSafeInteger(gradStatusNode.get("gpa")).asInt(0);
        academicAward.setAcademicSummary(new AcademicSummary(null, null, new GPA(creditHoursEarned, creditHoursEarned)));

        Student student = new Student();
        HighSchoolTranscript transcript = new HighSchoolTranscript();
        AcademicRecordBatch result = new AcademicRecordBatch();

        //set objects tree
        academicRecord.setAcademicAward(academicAward);
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
