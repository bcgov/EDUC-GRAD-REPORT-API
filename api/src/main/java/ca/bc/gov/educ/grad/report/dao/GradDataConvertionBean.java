package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.dto.ReportData;
import ca.bc.gov.educ.grad.report.dto.impl.*;
import ca.bc.gov.educ.grad.report.exception.InvalidParameterException;
import ca.bc.gov.educ.grad.report.model.achievement.Achievement;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementResult;
import ca.bc.gov.educ.grad.report.model.assessment.Assessment;
import ca.bc.gov.educ.grad.report.model.assessment.AssessmentResult;
import ca.bc.gov.educ.grad.report.model.graduation.Exam;
import ca.bc.gov.educ.grad.report.model.graduation.NonGradReason;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.student.StudentInfo;
import ca.bc.gov.educ.grad.report.model.transcript.GraduationData;
import ca.bc.gov.educ.grad.report.model.transcript.Transcript;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptCourse;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class GradDataConvertionBean {

    public StudentInfo getStudentInfo(ReportData reportData) {
        Student student = getStudent(reportData);
        School school = getSchool(reportData);
        GraduationData gradData = reportData.getGraduationData();
        List<String> programCodes = gradData == null ? new ArrayList<>() : gradData.getProgramCodes();
        Transcript transcript = reportData.getTranscript();
        StudentInfoImpl result = new StudentInfoImpl(
            student.getPen().getValue(),// String studNo,
            student.getFirstName(),// String firstName,
            student.getMiddleName(),// String middleName,
            student.getLastName(),// String lastName,
            student.getBirthdate() != null ? student.getBirthdate() : null,// Long birthdate,
            student.getEntityId(),// String localId,
            student.getGender(),// Character studGender,
            school.getMinistryCode(),// String mincode,
            student.getGrade(),// String studGrade,
            gradData != null ? gradData.getGraduationDate() : null,// Date gradDate,
            reportData.getGradProgram() != null ? reportData.getGradProgram().getCode().getCode() : null,// String gradReqtYear,
            reportData.getGradMessage(),// String gradMessage,
            reportData.getIssueDate() == null ? transcript != null ? transcript.getIssueDate() : null : reportData.getIssueDate(),// String updateDt,
            reportData.getLogo() == null ? reportData.getOrgCode() : reportData.getLogo(),// String logoType,
            student.getCurrentMailingAddress() != null ? student.getCurrentMailingAddress().getStreetLine1() : "",// String studentAddress1,
            student.getCurrentMailingAddress() != null ? student.getCurrentMailingAddress().getStreetLine2() : "",// String studentAddress2,
            student.getCurrentMailingAddress() != null ? student.getCurrentMailingAddress().getCity() : "",// String studentCity,
            student.getCurrentMailingAddress() != null ? student.getCurrentMailingAddress().getRegion() : "",// String studentProv,
            student.getCurrentMailingAddress() != null ? student.getCurrentMailingAddress().getPostalCode() : "",// String studentPostalCode,
            student.getCurrentMailingAddress() != null ? student.getCurrentMailingAddress().getCountryCode() : "",// String traxStudentCountry,
            student.getStudStatus(),// Character studStatus,
            gradData != null && gradData.getHonorsFlag() ? 'Y' : 'N', //Character honourFlag,
            gradData != null && gradData.getDogwoodFlag() ? 'Y' : 'N', //Character dogwoodFlag
            programCodes != null && programCodes.size() >= 1 ? programCodes.get(0) : null, //String prgmCode,
            programCodes != null && programCodes.size() >= 2 ? programCodes.get(1) : null, //String prgmCode2,
            programCodes != null && programCodes.size() >= 3 ? programCodes.get(2) : null, //String prgmCode3,
            programCodes != null && programCodes.size() >= 4 ? programCodes.get(3) : null, //String prgmCode4,
            programCodes != null && programCodes.size() >= 5 ? programCodes.get(4) : null, //String prgmCode5,
            school.getName(),// String schoolName,
            school.getPostalAddress() != null ? school.getPostalAddress().getStreetLine1() : "",// String schoolStreet,
            school.getPostalAddress() != null ? school.getPostalAddress().getStreetLine2() : "",// String schoolStreet2,
            school.getPostalAddress() != null ? school.getPostalAddress().getCity() : "",// String schoolCity,
            school.getPostalAddress() != null ? school.getPostalAddress().getRegion() : "",// String schoolProv,
            school.getPostalAddress() != null ? school.getPostalAddress().getPostalCode() : "",// String schoolPostalCode,
            school.getPhoneNumber(),// String schoolPhone,
            school.getTypeIndicator()// Character schlIndType
        );
        result.setNonGradReasons(this.getNongradReasons(reportData));
        return result;
    }

    public Transcript getTranscript(ReportData reportData) {
        return reportData.getTranscript();
    }

    public Achievement getAchievement(ReportData reportData) {
        return reportData.getAchievement();
    }

    public Student getStudent(ReportData reportData) {
        if(reportData.getStudent() == null || reportData.getStudent().getPen() == null) {
            throw new InvalidParameterException("Student and PEN can't be NULL");
        }
        StudentImpl student = (StudentImpl)reportData.getStudent();
        if(StringUtils.trimToNull(student.getEnglishCert()) == null) {
            student.setEnglishCert("Y");
        }
        if(StringUtils.trimToNull(student.getFrenchCert()) == null) {
            student.setFrenchCert("");
        }
        return student;
    }

    public School getSchool(ReportData reportData) {
        if(reportData.getSchool() == null || reportData.getSchool().getMinistryCode() == null) {
            throw new InvalidParameterException("School and mincode can't be NULL");
        }
        return reportData.getSchool();
    }

    public List<TranscriptCourse> getTranscriptCourses(ReportData reportData) {
        Student student = getStudent(reportData);
        List<TranscriptCourse> result = new ArrayList<>();
        if(reportData.getTranscript() != null) {
	        for(TranscriptResult r: reportData.getTranscript().getResults()) {
	            if(r.getCourse() == null || r.getMark() == null) {
	                throw new InvalidParameterException("Transcript Result Course and Mark can't be NULL");
	            }
	            TranscriptCourseImpl course = new TranscriptCourseImpl(
	                    student.getPen().getValue(), //String pen,
	                    r.getCourse().getName(), //String courseName,
	                    r.getCourse().getCode(), //String crseCode,
	                    r.getCourse().getLevel(), //String crseLevel,
	                    r.getCourse().getSessionDate(), //String sessionDate,
	                    r.getCourse().getCredits(), //String credits,
	                    r.getMark().getExamPercent(), //String examPercent,
	                    r.getMark().getSchoolPercent(), //String schoolPercent,
	                    r.getMark().getFinalPercent(), //String finalPercent,
	                    r.getMark().getFinalLetterGrade(), //String finalLetterGrade,
	                    r.getMark().getInterimPercent(), //String interimMark,
	                    r.getRequirementMet(), //String requirement,
	          null, //String specialCase,
	                    r.getCourse().getType() //Character courseType
	            );
	            result.add(course);
	        }
        }
        return result;
    }

    public List<AchievementCourse> getAchievementCourses(ReportData reportData) {
        Student student = getStudent(reportData);
        List<AchievementCourse> result = new ArrayList<>();
        if(reportData.getAchievement() == null) {
            return reportData.getStudentCourses();
        } else {
            for(AchievementResult r: reportData.getAchievement().getResults()) {
                if(r.getCourse() == null || r.getMark() == null) {
                    throw new InvalidParameterException("Achievement Result Course and Mark can't be NULL");
                }
                AchievementCourseImpl course = new AchievementCourseImpl(
                        student.getPen().getValue(), //String pen,
                        r.getCourse().getName(), //String courseName,
                        r.getCourse().getCode(), //String crseCode,
                        r.getCourse().getLevel(), //String crseLevel,
                        r.getCourse().getSessionDate(), //String sessionDate,
                        r.getCourse().getCredits(), //String credits,
                        r.getMark().getExamPercent(), //String examPercent,
                        r.getMark().getSchoolPercent(), //String schoolPercent,
                        r.getMark().getFinalPercent(), //String finalPercent,
                        r.getMark().getFinalLetterGrade(), //String finalLetterGrade,
                        r.getMark().getInterimPercent(), //String interimMark,
                        r.getRequirementMet(), //String requirement,
                        null, //String specialCase,
                        r.getCourse().getType(),
                        r.getUsedForGrad()//Character courseType
                );
                result.add(course);
            }
        }
        return result;
    }

    public List<Exam> getStudentExams(ReportData reportData) {
        Student student = getStudent(reportData);
        List<Exam> result = new ArrayList<>();
        if(reportData.getStudentExams() != null) {
            for(Exam r: reportData.getStudentExams()) {
                if(r.getCourseCode() == null || r.getSessionDate() == null) {
                    throw new InvalidParameterException("Exam code and Session date can't be NULL");
                }
                ExamImpl exam = new ExamImpl(
                        r.getCourseCode(),//courseCode
                        r.getCourseName(),//courseName
                        r.getCourseLevel(),//courseLevel;
                        r.getSessionDate(),//sessionDate;
                        r.getGradReqMet(),//gradReqMet;
                        r.getCompletedCoursePercentage(),//completedCoursePercentage;
                        r.getCompletedCourseLetterGrade(),//completedCourseLetterGrade;
                        r.getBestSchoolPercent(),//bestSchoolPercent;
                        r.getBestExamPercent(),//bestExamPercent;
                        r.getInterimPercent(),//interimPercent;
                        r.getEquivOrChallenge(),//equivOrChallenge;
                        r.getMetLitNumRequirement(),//metLitNumRequirement;
                        r.getCredits(),//credits;
                        r.getCreditsUsedForGrad()//creditsUsedForGrad;
                );
                result.add(exam);
            }
        }
        return result;
    }

    public List<OptionalProgram> getOptionalPrograms(ReportData reportData) {
        Student student = getStudent(reportData);
        List<OptionalProgram> result = new ArrayList<>();
        if(reportData.getOptionalPrograms() != null) {
            for(OptionalProgram r: reportData.getOptionalPrograms()) {
                if(r.getOptionalProgramCode() == null || r.getOptionalProgramName() == null) {
                    throw new InvalidParameterException("Optional Program code and name can't be NULL");
                }
                OptionalProgramImpl program = new OptionalProgramImpl(
                    r.getOptionalProgramCode(),//optionalProgramCode;
                    r.getOptionalProgramName(),//optionalProgramName;
                    r.getProgramCompletionDate(),//programCompletionDate;
                    r.getHasRequirementMet(),//hasRequirementMet;
                    r.getRequirementMet(),//requirementMet;
                    r.getNonGradReasons()//nonGradReasons;
                );
                result.add(program);
            }
        }
        return result;
    }

    public HashMap<String, String> getNongradReasons(ReportData reportData) {
        final HashMap<String, String> reasons = new HashMap<>();
        if(reportData.getNonGradReasons() != null) {
            for (NonGradReason reason : reportData.getNonGradReasons()) {
                reasons.put(reason.getCode(), reason.getDescription());
            }
        }
        return reasons;
    }

    public List<AssessmentResult> getAssessmentCourses(ReportData reportData) {
        StudentInfo studentInfo = getStudentInfo(reportData);
        List<AssessmentResult> result = new ArrayList<>();
        Assessment assessment = reportData.getAssessment();
        for(AssessmentResult r: assessment.getResults()) {
            AssessmentResultImpl assessmentResult = new AssessmentResultImpl();
            assessmentResult.setStudentNumber(studentInfo.getPen());
            assessmentResult.setAssessmentName(r.getAssessmentName());
            assessmentResult.setAssessmentCode(r.getAssessmentCode());
            assessmentResult.setSessionDate(r.getSessionDate());
            assessmentResult.setGradReqMet(r.getGradReqMet());
            assessmentResult.setSpecialCase(r.getSpecialCase());
            assessmentResult.setExceededWriteFlag(r.getExceededWriteFlag());
            assessmentResult.setProficiencyScore(r.getProficiencyScore());
            result.add(assessmentResult);
        }
        return result;
    }
}
