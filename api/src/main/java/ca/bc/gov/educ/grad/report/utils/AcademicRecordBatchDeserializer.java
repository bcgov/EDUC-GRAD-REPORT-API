package ca.bc.gov.educ.grad.report.utils;

import ca.bc.gov.educ.grad.report.dto.reports.xml.*;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Iterator;

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
        String middleName = nullSafeString(root.get("legalMiddleName")).asText("");
        String lastName = nullSafeString(root.get("legalLastName")).asText("");
        person.setName(new Name(firstName, middleName, lastName));

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

        String deceased = root.get("deceasedDate") == null ? "FALSE" : "TRUE";
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
        switch(studentLevelCode) {
            case "8":
                academicRecord.setStudentLevel(new StudentLevel("EighthGrade"));
                break;
            case "9":
                academicRecord.setStudentLevel(new StudentLevel("NinthGrade"));
                break;
            case "10":
                academicRecord.setStudentLevel(new StudentLevel("TenthGrade"));
                break;
            case "11":
                academicRecord.setStudentLevel(new StudentLevel("EleventhGrade"));
                break;
            case "12":
                academicRecord.setStudentLevel(new StudentLevel("TwelfthGrade"));
                break;
            case "AD":
                academicRecord.setStudentLevel(new StudentLevel("TwelfthGrade"));
                break;
            default:
                academicRecord.setStudentLevel(new StudentLevel("Ungraded"));
                break;
        }

        AcademicAward academicAward = new AcademicAward();

        String academicAwardLevel = nullSafeString(root.get("gradeCode")).asText("");
        academicAward.setAcademicAwardLevel(academicAwardLevel);

        JsonNode gradStatusNode = nodeTree.get("gradStatus");
        String academicCompletionDate = nullSafeString(gradStatusNode.get("programCompletionDate")).asText("");
        academicAward.setAcademicCompletionDate(academicCompletionDate);
        String academicAwardTitle = nullSafeString(gradStatusNode.get("program")).asText("");
        Integer creditHoursRequired = 0;
        for (GraduationProgramCode graduationProgramCode : GraduationProgramCode.values()) {
            if (StringUtils.contains(academicAwardTitle, graduationProgramCode.getCode())) {
                creditHoursRequired = graduationProgramCode.getCredits();
                academicAward.setAcademicAwardTitle(graduationProgramCode.getDescription());
                break;
            }
        }

        String honorsTitle = nullSafeString(gradStatusNode.get("honoursStanding")).asText("");
        academicAward.setAcademicHonors(new AcademicHonors(honorsTitle.equalsIgnoreCase("Y") ? "true" : null));

        String academinCompletionDate = nullSafeString(gradStatusNode.get("programCompletionDate")).asText("");
        academicAward.setAcademicCompletionDate(academinCompletionDate);

        String academicCompletionIndicator = nullSafeString(root.get("graduated")).asText("");
        academicAward.setAcademicCompletionIndicator(academicCompletionIndicator);

        Integer creditHoursEarned = 0;

        JsonNode studentCoursesNode = nodeTree.get("studentCourses");
        if(studentCoursesNode != null) {
            JsonNode studentCourseListNode = studentCoursesNode.get("studentCourseList");
            if(studentCourseListNode != null) {
                Iterator<JsonNode> studentCourseListNodeIterator = studentCourseListNode.iterator();
                while(studentCourseListNodeIterator.hasNext()) {
                    JsonNode studentCourseNode = studentCourseListNodeIterator.next();
                    String courseCreditLevel = nullSafeString(studentCourseNode.get("courseLevel")).asText("");
                    String sessionName = nullSafeString(studentCourseNode.get("sessionDate")).asText("");
                    Integer courseCreditValue = nullSafeInteger(studentCourseNode.get("originalCredits")).asInt(0);
                    creditHoursEarned = creditHoursEarned + nullSafeInteger(studentCourseNode.get("creditsUsedForGrad")).asInt(0);
                    String courseAcademicGrade = nullSafeString(studentCourseNode.get("completedCourseLetterGrade")).asText("");
                    String courseNumber = nullSafeString(studentCourseNode.get("courseCode")).asText("");
                    String courseTitle = nullSafeString(studentCourseNode.get("courseName")).asText("");
                    academicRecord.addAcademicSessionCourse(sessionName, new Course(
                        "Regular",
                        courseCreditLevel,
                        courseCreditValue,
                        0, //CourseAcademicGradeScaleCode
                        courseAcademicGrade,
                        null, //CourseSupplementalAcademicGrade;
                        null, //CourseAcademicGradeStatusCode;
                        null, //CourseSubjectAbbreviation;
                        courseNumber, //CourseNumber;
                        courseTitle,
                        null //Requirement;
                    ));
                }
            }
        }

        JsonNode nonGradReasonsNode = nodeTree.get("nonGradReasons");
        StringBuilder noteMessage = new StringBuilder("BC: Reason not graduated:");
        if(nonGradReasonsNode != null) {
            Iterator<JsonNode> nonGradReasonsNodeIterator = nonGradReasonsNode.iterator();
            while(nonGradReasonsNodeIterator.hasNext()) {
                JsonNode nonGradReasonNode = nonGradReasonsNodeIterator.next();
                String rule = nullSafeString(nonGradReasonNode.get("rule")).asText("");
                String description = nullSafeString(nonGradReasonNode.get("description")).asText("");
                noteMessage.append("[").append(rule).append("]").append(" ").append(description).append(";");
            }
        }


        academicAward.setAcademicSummary(new AcademicSummary(null, null, noteMessage.toString(), new GPA(creditHoursEarned, creditHoursRequired)));

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
