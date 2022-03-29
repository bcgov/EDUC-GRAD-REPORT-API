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
import org.apache.commons.lang3.StringUtils;

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
        return parseAcademicRecordBatch(nodeTree);
    }

    private AcademicRecordBatch parseAcademicRecordBatch(JsonNode nodeTree) {
        Student student = parseStudent(nodeTree);
        HighSchoolTranscript transcript = new HighSchoolTranscript(student);
        return new AcademicRecordBatch(transcript);
    }

    private Student parseStudent(JsonNode nodeTree) {
        JsonNode root = nodeTree.get("gradStudent");

        AcademicRecord academicRecord = parseAcademicRecord(root);
        JsonNode schoolNode = nodeTree.get("school");
        School school = parseSchool(schoolNode);
        academicRecord.setSchool(school);

        Integer creditHoursEarned = parseCourses(nodeTree.get("studentCourses"), academicRecord);
        AcademicAward academicAward = parseAcademicAward(root, nodeTree.get("gradStatus"), nodeTree.get("nonGradReasons"), creditHoursEarned);
        academicRecord.setAcademicAward(academicAward);

        return new Student(parsePerson(root), academicRecord);
    }

    private AcademicRecord parseAcademicRecord(JsonNode root) {
        AcademicRecord academicRecord = new AcademicRecord();
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
        return academicRecord;
    }

    private AcademicAward parseAcademicAward(JsonNode root, JsonNode gradStatusNode, JsonNode nonGradReasonsNode, Integer creditHoursEarned) {

        AcademicAward academicAward = new AcademicAward();
        String academicAwardLevel = nullSafeString(root.get("gradeCode")).asText("");
        academicAward.setAcademicAwardLevel(academicAwardLevel);

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
        academicAward.setAcademicHonors(new AcademicHonors(honorsTitle.equalsIgnoreCase("Y") ? "honours" : null));

        String academinCompletionDate = nullSafeString(gradStatusNode.get("programCompletionDate")).asText("");
        academicAward.setAcademicCompletionDate(academinCompletionDate);

        String academicCompletionIndicator = nullSafeString(root.get("graduated")).asText("");
        academicAward.setAcademicCompletionIndicator(academicCompletionIndicator);

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

        academicAward.setAcademicSummary(new AcademicSummary("All", "Secondary", noteMessage.toString(), new GPA(creditHoursEarned, creditHoursRequired)));

        return academicAward;
    }

    private Person parsePerson(JsonNode root) {
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

        String deceased = "".equals(nullSafeString(root.get("deceasedDate")).asText("")) ? "FALSE" : "TRUE";
        person.setDeceased(new Deceased(deceased));
        return person;
    }

    private Integer parseCourses(JsonNode studentCoursesNode, AcademicRecord academicRecord) {
        Integer creditHoursEarned = 0;
        if(studentCoursesNode != null) {
            JsonNode studentCourseListNode = studentCoursesNode.get("studentCourseList");
            if(studentCourseListNode != null) {
                Iterator<JsonNode> studentCourseListNodeIterator = studentCourseListNode.iterator();
                while(studentCourseListNodeIterator.hasNext()) {
                    JsonNode studentCourseNode = studentCourseListNodeIterator.next();

                    String courseCode = nullSafeString(studentCourseNode.get("courseCode")).asText("");
                    String courseLevel = nullSafeString(studentCourseNode.get("courseLevel")).asText("");
                    String originalCourseID = courseCode + " " + courseLevel;

                    String courseCreditLevel = "Ungraded";
                    String courseNumber = courseLevel;
                    if(StringUtils.contains("GT;GTF;PORT;PORTF", courseCode)) {
                        courseCreditLevel = "Ungraded";
                        courseNumber = "";
                    } else {
                        switch(StringUtils.substring(courseLevel, 0, 2)) {
                            case "10":
                                courseCreditLevel = "TenthGrade";
                                break;
                            case "11":
                                courseCreditLevel = "EleventhGrade";
                                break;
                            case "12":
                                courseCreditLevel = "TwelfthGrade";
                                break;
                        }
                    }

                    String sessionName = nullSafeString(studentCourseNode.get("sessionDate")).asText("");
                    Integer courseCreditValue = nullSafeInteger(studentCourseNode.get("originalCredits")).asInt(0);
                    Integer courseCreditEarned = courseCreditValue;
                    creditHoursEarned = creditHoursEarned + nullSafeInteger(studentCourseNode.get("creditsUsedForGrad")).asInt(0);
                    String finalLetterGrade = nullSafeString(studentCourseNode.get("completedCourseLetterGrade")).asText("");
                    String bestExampPersent = nullSafeString(studentCourseNode.get("bestExamPercent")).asText("");
                    String bestSchoolpPersent = nullSafeString(studentCourseNode.get("bestSchoolPercent")).asText("");
                    String courseAcademicGrade = nullSafeString(studentCourseNode.get("completedCourseLetterGrade"), studentCourseNode.get("interimLetterGrade")).asText("");
                    String courseSubjectAbbreviation = nullSafeString(studentCourseNode.get("courseCode")).asText("");
                    String courseTitle = nullSafeString(studentCourseNode.get("courseName")).asText("");

                    String rapCode = nullSafeString(studentCourseNode.get("gradReqMet")).asText("");
                    String rapName = nullSafeString(studentCourseNode.get("gradReqMetDetail")).asText("");
                    String conditionMetCode = nullSafeString(studentCourseNode.get("creditsUsedForGrad")).asText("");

                    academicRecord.addAcademicSessionCourse(sessionName, new Course(
                            "Regular",
                            courseCreditLevel,
                            courseCreditValue,
                            courseCreditEarned,
                            53, //CourseAcademicGradeScaleCode
                            courseAcademicGrade,
                            new CourseSupplementalAcademicGrade(new CourseSupplementalGrade(
                                    "ExamGrade",
                                    "89",
                                    !"".equals(finalLetterGrade) ? "Completed" : "In Progress",
                                    bestExampPersent
                            ),
                                    new CourseSupplementalGrade(
                                            "InstructorAssignedGrade",
                                            "89",
                                            !"".equals(finalLetterGrade) ? "Completed" : "In Progress",
                                            bestSchoolpPersent
                                    ),
                                    new CourseSupplementalGrade(
                                            "BlendedFinalGrade",
                                            "89",
                                            !"".equals(finalLetterGrade) ? "Completed" : "In Progress",
                                            !"".equals(finalLetterGrade) ? "FINAL_PCT" : "INTERIM_PCT"
                                    )
                            ), //CourseSupplementalAcademicGrade;
                            !"".equals(finalLetterGrade) ? "Completed" : "In Progress", //CourseAcademicGradeStatusCode;
                            courseSubjectAbbreviation, //CourseSubjectAbbreviation;
                            courseNumber, //CourseNumber;
                            courseTitle,
                            originalCourseID,
                            new Requirement(
                                    rapCode,
                                    rapName,
                                    !"".equals(conditionMetCode) ? "Yes" : "No"
                            ) //Requirement;
                    ));
                }
            }
        }
        return creditHoursEarned;
    }

    private School parseSchool(JsonNode schoolNode ) {
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
        return school;
    }

    private JsonNode nullSafeString(final JsonNode currentNode, final JsonNode alternateNode) {
        return currentNode == null ? (alternateNode == null ? new TextNode("") : alternateNode) : currentNode;
    }

    private JsonNode nullSafeString(final JsonNode currentNode) {
        return currentNode == null ? new TextNode("") : currentNode;
    }

    private JsonNode nullSafeInteger(final JsonNode currentNode) {
        return currentNode == null ? new IntNode(0) : currentNode;
    }

    private JsonNode nullSafeBoolean(final JsonNode currentNode) {
        return currentNode == null ? BooleanNode.FALSE : currentNode;
    }

}
