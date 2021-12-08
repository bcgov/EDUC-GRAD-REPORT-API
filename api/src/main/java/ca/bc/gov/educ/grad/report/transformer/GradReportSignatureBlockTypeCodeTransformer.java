package ca.bc.gov.educ.grad.report.transformer;

import ca.bc.gov.educ.grad.report.dto.SignatureBlockTypeCode;
import ca.bc.gov.educ.grad.report.entity.SignatureBlockTypeCodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradReportSignatureBlockTypeCodeTransformer {

    @Autowired
    ModelMapper modelMapper;

    public List<SignatureBlockTypeCode> transformToDTO (List<SignatureBlockTypeCodeEntity> entities ) {
        List<SignatureBlockTypeCode> codes = new ArrayList<SignatureBlockTypeCode>();
        for (SignatureBlockTypeCodeEntity entity : entities) {
            SignatureBlockTypeCode code = modelMapper.map(entity, SignatureBlockTypeCode.class);
            codes.add(code);
        }
        return codes;
    }

    public SignatureBlockTypeCode transformToDTO (SignatureBlockTypeCodeEntity entity ) {
        SignatureBlockTypeCode code = modelMapper.map(entity, SignatureBlockTypeCode.class);
        return code;
    }

    public SignatureBlockTypeCodeEntity transformToEntity(SignatureBlockTypeCode code) {
        SignatureBlockTypeCodeEntity GragReportSignatureImageEntity = modelMapper.map(code, SignatureBlockTypeCodeEntity.class);
        return GragReportSignatureImageEntity;
    }
}
