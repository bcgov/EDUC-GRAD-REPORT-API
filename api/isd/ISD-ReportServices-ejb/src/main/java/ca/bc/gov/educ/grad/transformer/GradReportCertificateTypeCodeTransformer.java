package ca.bc.gov.educ.grad.transformer;

import ca.bc.gov.educ.grad.dto.CertificateTypeCode;
import ca.bc.gov.educ.grad.entity.CertificateTypeCodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradReportCertificateTypeCodeTransformer {

    @Autowired
    ModelMapper modelMapper;

    public List<CertificateTypeCode> transformToDTO (List<CertificateTypeCodeEntity> entities ) {
        List<CertificateTypeCode> codes = new ArrayList<CertificateTypeCode>();
        for (CertificateTypeCodeEntity entity : entities) {
            CertificateTypeCode code = modelMapper.map(entity, CertificateTypeCode.class);
            codes.add(code);
        }
        return codes;
    }

    public CertificateTypeCode transformToDTO (CertificateTypeCodeEntity entity ) {
        CertificateTypeCode code = modelMapper.map(entity, CertificateTypeCode.class);
        return code;
    }
}
