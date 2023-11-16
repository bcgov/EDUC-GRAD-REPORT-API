package ca.bc.gov.educ.grad.report.dao;

import ca.bc.gov.educ.grad.report.entity.CertificateTypeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CertificateTypeCodeRepository extends JpaRepository<CertificateTypeCodeEntity, String> {

    @Query("select c from CertificateTypeCodeEntity c where c.certificateTypeCode=:code")
    CertificateTypeCodeEntity findByCertificateCode(String code);

    @Query("select t from CertificateTypeCodeEntity t join StudentCertificateEntity c on t.certificateTypeCode = c.certificateTypeCode where c.documentStatusCode='COMPL' and c.graduationStudentRecordId=:graduationStudentRecordId")
    List<CertificateTypeCodeEntity> getStudentCertificateTypes(UUID graduationStudentRecordId);

    @Query("select t from CertificateTypeCodeEntity t join StudentCertificateEntity c on t.certificateTypeCode = c.certificateTypeCode where c.documentStatusCode='COMPL' and c.graduationStudentRecordId=:graduationStudentRecordId and c.certificateTypeCode in (:certificateTypeCode)")
    List<CertificateTypeCodeEntity> getStudentCertificateTypes(UUID graduationStudentRecordId, List<String> certificateTypeCode);
}