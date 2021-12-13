package ca.bc.gov.educ.grad.report.transformer;

import ca.bc.gov.educ.grad.report.dto.TranscriptTypeCode;
import ca.bc.gov.educ.grad.report.entity.TranscriptTypeCodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradReportTranscriptTypeCodeTransformer {

    @Autowired
    ModelMapper modelMapper;

    public List<TranscriptTypeCode> transformToDTO (List<TranscriptTypeCodeEntity> entities ) {
        List<TranscriptTypeCode> codes = new ArrayList<TranscriptTypeCode>();
        for (TranscriptTypeCodeEntity entity : entities) {
            TranscriptTypeCode code = modelMapper.map(entity, TranscriptTypeCode.class);
            codes.add(code);
        }
        return codes;
    }

    public TranscriptTypeCode transformToDTO (TranscriptTypeCodeEntity entity ) {
        TranscriptTypeCode code = modelMapper.map(entity, TranscriptTypeCode.class);
        return code;
    }
}
