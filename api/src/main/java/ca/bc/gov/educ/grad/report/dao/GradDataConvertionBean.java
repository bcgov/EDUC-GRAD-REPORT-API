package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.api.client.*;
import ca.bc.gov.educ.grad.report.dto.impl.*;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.AchievementOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.CertificateOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.TranscriptOrderTypeImpl;
import ca.bc.gov.educ.grad.report.exception.InvalidParameterException;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse;
import ca.bc.gov.educ.grad.report.model.assessment.AssessmentResult;
import ca.bc.gov.educ.grad.report.model.cert.Certificate;
import ca.bc.gov.educ.grad.report.model.cert.CertificateType;
import ca.bc.gov.educ.grad.report.model.common.party.address.PostalDeliveryInfo;
import ca.bc.gov.educ.grad.report.model.graduation.Exam;
import ca.bc.gov.educ.grad.report.model.graduation.OptionalProgram;
import ca.bc.gov.educ.grad.report.model.order.OrderType;
import ca.bc.gov.educ.grad.report.model.reports.PaperType;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.Student;
import ca.bc.gov.educ.grad.report.model.student.StudentInfo;
import ca.bc.gov.educ.grad.report.model.transcript.Transcript;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptCourse;
import ca.bc.gov.educ.grad.report.model.transcript.TranscriptTypeCode;
import ca.bc.gov.educ.grad.report.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradDataConvertionBean extends BaseServiceImpl implements Serializable {

    public StudentInfo getStudentInfo(ReportData reportData) {
        Student student = getStudent(reportData);
        School school = getSchool(reportData);
        ca.bc.gov.educ.grad.report.api.client.GraduationData gradData = reportData.getGraduationData();
        List<String> programCodes = gradData == null ? new ArrayList<>() : gradData.getProgramCodes();
        ca.bc.gov.educ.grad.report.api.client.Transcript transcript = reportData.getTranscript();
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
            reportData.getGradProgram() != null ? reportData.getGradProgram().getCode().getDescription() : student.getGradProgram(),// String gradProgram,
            reportData.getGradProgram() != null ? reportData.getGradProgram().getCode().getCode() : student.getGradReqYear(),// String gradReqYear,
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
        result.setHasOtherProgram(student.getHasOtherProgram());
        result.setOtherProgramParticipation(student.getOtherProgramParticipation());
        return result;
    }

    public Transcript getTranscript(ReportData reportData) {
        ca.bc.gov.educ.grad.report.api.client.Transcript clientTranscript = reportData.getTranscript();
        TranscriptImpl transcript = new TranscriptImpl();
        BeanUtils.copyProperties(clientTranscript, transcript);
        transcript.setInterim("true".equalsIgnoreCase(clientTranscript.getInterim()));
        Code clientTranscriptTypeCode = clientTranscript.getTranscriptTypeCode();
        TranscriptTypeCode transcriptTypeCode = TranscriptTypeCode.valueFrom(clientTranscriptTypeCode.getCode());
        transcript.setTranscriptTypeCode(transcriptTypeCode);
        return transcript;
    }

    public Student getStudent(ReportData reportData) {
        if(reportData.getStudent() == null || reportData.getStudent().getPen() == null) {
            throw new InvalidParameterException("Student and PEN can't be NULL");
        }
        StudentImpl student = new StudentImpl();
        BeanUtils.copyProperties(reportData.getStudent(), student);
        student.setPen(new PersonalEducationNumberObject(reportData.getStudent().getPen().getPen()));
        if(reportData.getStudent().getAddress() != null) {
            PostalAddressImpl address = new PostalAddressImpl();
            BeanUtils.copyProperties(reportData.getStudent().getAddress(), address);
            student.setCurrentMailingAddress(address);
        }

        List<ca.bc.gov.educ.grad.report.model.graduation.OtherProgram> otherPrograms = new ArrayList<>();
        if(reportData.getStudent().getOtherProgramParticipation() != null) {
            for (OtherProgram p : reportData.getStudent().getOtherProgramParticipation()) {
                OtherProgramImpl otherProgram = new OtherProgramImpl();
                otherProgram.setProgramCode(p.getProgramCode());
                otherProgram.setProgramName(p.getProgramName());
                otherPrograms.add(otherProgram);
            }
            student.setOtherProgramParticipation(otherPrograms);
        }

        if(StringUtils.trimToNull(student.getEnglishCert()) == null &&
                StringUtils.trimToNull(student.getFrenchCert()) == null) {
            student.setEnglishCert("E");
        } else if(StringUtils.trimToNull(student.getEnglishCert()) == null) {
            student.setEnglishCert("");
        }
        if(StringUtils.trimToNull(student.getFrenchCert()) == null) {
            student.setFrenchCert("");
        }
        return student;
    }

    public School getSchool(ReportData reportData) {
        if(reportData.getSchool() == null || reportData.getSchool().getMincode() == null) {
            throw new InvalidParameterException("School and mincode can't be NULL");
        }
        SchoolImpl school = new SchoolImpl();
        BeanUtils.copyProperties(reportData.getSchool(), school);
        if(reportData.getSchool().getAddress() != null) {
            PostalAddressImpl address = new PostalAddressImpl();
            BeanUtils.copyProperties(reportData.getSchool().getAddress(), address);
            school.setAddress(address);
        }
        return school;
    }

    public List<TranscriptCourse> getTranscriptCourses(ReportData reportData) {
        Student student = getStudent(reportData);
        List<TranscriptCourse> result = new ArrayList<>();
        if(reportData.getTranscript() != null) {
	        for(ca.bc.gov.educ.grad.report.api.client.TranscriptResult r: reportData.getTranscript().getResults()) {
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
                        r.getMark().getInterimLetterGrade(), //String interimLetterGrade,
	                    r.getRequirement(), //String requirement,
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
            for(ca.bc.gov.educ.grad.report.api.client.AchievementCourse r: reportData.getStudentCourses()) {
                AchievementCourseImpl course = new AchievementCourseImpl();
                BeanUtils.copyProperties(r, course);
                result.add(course);
        }
        return result;
    }

    public List<Exam> getStudentExams(ReportData reportData) {
        List<Exam> result = new ArrayList<>();
        if(reportData.getStudentExams() != null) {
            for(ca.bc.gov.educ.grad.report.api.client.Exam r: reportData.getStudentExams()) {
                if(r.getCourseCode() == null || r.getSessionDate() == null) {
                    throw new InvalidParameterException("Exam code and Session date can't be NULL");
                }
                ExamImpl exam = new ExamImpl();
                BeanUtils.copyProperties(r, exam);
                result.add(exam);
            }
        }
        return result;
    }

    public List<OptionalProgram> getOptionalPrograms(ReportData reportData) {
        Student student = getStudent(reportData);
        List<OptionalProgram> result = new ArrayList<>();
        if(reportData.getOptionalPrograms() != null) {
            for(ca.bc.gov.educ.grad.report.api.client.OptionalProgram r: reportData.getOptionalPrograms()) {
                if(r.getOptionalProgramCode() == null || r.getOptionalProgramName() == null) {
                    throw new InvalidParameterException("Optional Program code and name can't be NULL");
                }
                List<ca.bc.gov.educ.grad.report.model.graduation.GradRequirement> requirementMet = new ArrayList();
                for(GradRequirement gr: r.getRequirementMet()) {
                    ca.bc.gov.educ.grad.report.model.graduation.GradRequirement data = new GradRequirementImpl();
                    BeanUtils.copyProperties(gr, data);
                    List<AchievementCourse> acrs = new ArrayList<>();
                    for(ca.bc.gov.educ.grad.report.api.client.AchievementCourse ac: gr.getCourseDetails()) {
                        AchievementCourseImpl course = new AchievementCourseImpl();
                        BeanUtils.copyProperties(ac, course);
                        acrs.add(course);
                    }
                    data.setCourseDetails(acrs);
                    requirementMet.add(data);
                }
                List<ca.bc.gov.educ.grad.report.model.graduation.NonGradReason> nonGradReasons = new ArrayList();
                for(ca.bc.gov.educ.grad.report.api.client.NonGradReason gr: r.getNonGradReasons()) {
                    ca.bc.gov.educ.grad.report.model.graduation.NonGradReason data = new NonGradReasonImpl();
                    BeanUtils.copyProperties(gr, data);
                    nonGradReasons.add(data);
                }
                OptionalProgramImpl program = new OptionalProgramImpl();
                BeanUtils.copyProperties(r, program);
                program.setRequirementMet(requirementMet);
                program.setNonGradReasons(nonGradReasons);
                result.add(program);
            }
        }
        return result;
    }

    public HashMap<String, String> getNongradReasons(ReportData reportData) {
        final HashMap<String, String> reasons = new HashMap<>();
        if(reportData.getNonGradReasons() != null) {
            for (ca.bc.gov.educ.grad.report.api.client.NonGradReason reason : reportData.getNonGradReasons()) {
                reasons.put(reason.getCode(), reason.getDescription());
            }
        }
        return reasons;
    }

    public List<AssessmentResult> getAssessmentCourses(ReportData reportData) {
        StudentInfo studentInfo = getStudentInfo(reportData);
        List<AssessmentResult> result = new ArrayList<>();
        ca.bc.gov.educ.grad.report.api.client.Assessment assessment = reportData.getAssessment();
        for(ca.bc.gov.educ.grad.report.api.client.AssessmentResult r: assessment.getResults()) {
            AssessmentResultImpl assessmentResult = new AssessmentResultImpl();
            BeanUtils.copyProperties(r, assessmentResult);
            result.add(assessmentResult);
        }
        return result;
    }

    public Certificate getCertificate(ca.bc.gov.educ.grad.report.api.client.Certificate certificate) {
        CertificateImpl result = new CertificateImpl();
        BeanUtils.copyProperties(certificate, result);
        final CertificateType rsRptType = getCertificateType(certificate.getOrderType().getCertificateType().getReportName());
        CertificateOrderTypeImpl orderType = new CertificateOrderTypeImpl(rsRptType);
        orderType.setName(certificate.getOrderType().getName());
        result.setOrderType(orderType);
        return result;
    }

    public PostalDeliveryInfo getPostalDeliveryInfo(ReportData reportData) {
        PackingSlip packingSlip = reportData.getPackingSlip();
        if(packingSlip == null || packingSlip.getSchool() == null) {
            throw new InvalidParameterException("Packing Slip and School can't be NULL");
        }
        if(packingSlip.getSchool().getAddress() == null) {
            throw new InvalidParameterException("School Address can't be NULL");
        }
        PostalDeliveryInfo deliveryInfo = new PostalDeliveryInfoImpl();
        deliveryInfo.setName(packingSlip.getSchool().getMincode() + " " + packingSlip.getSchool().getName());
        deliveryInfo.setAttentionTo(packingSlip.getRecipient());
        deliveryInfo.setStreetLine1(packingSlip.getSchool().getAddress().getStreetLine1());
        deliveryInfo.setStreetLine2(packingSlip.getSchool().getAddress().getStreetLine2());
        deliveryInfo.setCity(packingSlip.getSchool().getAddress().getCity());
        deliveryInfo.setRegion(packingSlip.getSchool().getAddress().getRegion());
        deliveryInfo.setCountryCode(packingSlip.getSchool().getAddress().getCountry());
        deliveryInfo.setPostalCode(packingSlip.getSchool().getAddress().getCode());

        return deliveryInfo;
    }

    public OrderType getPackingSlipOrderType(ReportData reportData) {
        PackingSlip packingSlip = reportData.getPackingSlip();
        if(packingSlip == null || packingSlip.getOrderType() == null) {
            throw new InvalidParameterException("Packing Slip can't be NULL");
        }
        OrderType result;
         switch(packingSlip.getOrderType().getName()) {
             case "Certificate" : {
                 CertificateOrderTypeImpl orderType = new CertificateOrderTypeImpl();
                 orderType.setName(packingSlip.getOrderType().getName());
                 CertificateType certificateType = CertificateType.forValue(packingSlip.getOrderType().getPackingSlipType().getName());
                 certificateType.setPaperType(PaperType.forValue(packingSlip.getOrderType().getPackingSlipType().getPaperType().getCode()));
                 orderType.setCertificateType(certificateType);
                 result = orderType;
             }
                break;
             case "Transcript" : {
                 TranscriptOrderTypeImpl orderType = new TranscriptOrderTypeImpl();
                 orderType.setName(packingSlip.getOrderType().getName());
                 orderType.setPaperType(PaperType.forValue(packingSlip.getOrderType().getPackingSlipType().getPaperType().getCode()));
                 result = orderType;
             }
                 break;
             case "Achievement" : {
                 AchievementOrderTypeImpl orderType = new AchievementOrderTypeImpl();
                 orderType.setName(packingSlip.getOrderType().getName());
                 orderType.setPaperType(PaperType.forValue(packingSlip.getOrderType().getPackingSlipType().getPaperType().getCode()));
                 result = orderType;
             }
                 break;
             default:
                 throw new InvalidParameterException("Order Type is not valid");
         }
        return result;
    }

    private CertificateType getCertificateType(String reportName) {
        final CertificateType rsRptType;
        switch(reportName) {
            case "E":
                rsRptType = CertificateType.E;
                break;
            case "A":
                rsRptType = CertificateType.A;
                break;
            case "EI":
                rsRptType = CertificateType.EI;
                break;
            case "AI":
                rsRptType = CertificateType.AI;
                break;
            case "S":
                rsRptType = CertificateType.S;
                break;
            case "SC":
                rsRptType = CertificateType.SC;
                break;
            case "SCF":
                rsRptType = CertificateType.SCF;
                break;
            case "F":
                rsRptType = CertificateType.F;
                break;
            case "O":
                rsRptType = CertificateType.O;
                break;
            default:
                rsRptType = CertificateType.E;
        }
        return rsRptType;
    }

    public List<Student> getStudents(ReportData reportData) {
        List<Student> result = new ArrayList<>();
        ca.bc.gov.educ.grad.report.api.client.School school = reportData.getSchool();
        List<ca.bc.gov.educ.grad.report.api.client.Student> students = school.getStudents();
        for(ca.bc.gov.educ.grad.report.api.client.Student st: students) {
            StudentImpl student = new StudentImpl();
            BeanUtils.copyProperties(st, student);
            student.setPen(new PersonalEducationNumberObject(st.getPen().getPen()));
            if(st.getAddress() != null) {
                PostalAddressImpl address = new PostalAddressImpl();
                BeanUtils.copyProperties(st.getAddress(), address);
                student.setCurrentMailingAddress(address);
            }
            GraduationDataImpl gradData = new GraduationDataImpl();
            GraduationData graduationData = st.getGraduationData();
            BeanUtils.copyProperties(graduationData, gradData);
            student.setGraduationData(gradData);
            for(ca.bc.gov.educ.grad.report.api.client.NonGradReason rsn: st.getNonGradReasons()) {
                NonGradReasonImpl reason = new NonGradReasonImpl();
                BeanUtils.copyProperties(rsn, reason);
                student.getNonGradReasons().add(reason);
            }
            result.add(student);
        }
        return result;
    }

    public List<String> getCarrierPrograms(ReportData reportData) {
        List<String> result = new ArrayList<>();
        Student student = getStudent(reportData);
        String pen = student.getPen().getPen();
        GradSearchStudent gradSearchStudent = this.getStudentByPenFromStudentApi(pen, reportData.getAccessToken());
        if(gradSearchStudent != null) {
            GraduationStudentRecord graduationStudentRecord = this.getGradStatusFromGradStudentApi(gradSearchStudent.getStudentID(), reportData.getAccessToken());
            List<CareerProgram> careerPrograms = graduationStudentRecord.getCareerPrograms();
            if (careerPrograms != null) {
                result.addAll(careerPrograms.stream().map(CareerProgram::getCareerProgramCode).collect(Collectors.toList()));
            }
        }
        return result;
    }
}
