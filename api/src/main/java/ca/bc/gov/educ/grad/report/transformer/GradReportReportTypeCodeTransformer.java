package ca.bc.gov.educ.grad.report.transformer;

import ca.bc.gov.educ.grad.report.dto.ReportTypeCode;
import ca.bc.gov.educ.grad.report.entity.ReportTypeCodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradReportReportTypeCodeTransformer {

    @Autowired
    ModelMapper modelMapper;

    public List<ReportTypeCode> transformToDTO (List<ReportTypeCodeEntity> entities ) {
        List<ReportTypeCode> codes = new ArrayList<ReportTypeCode>();
        for (ReportTypeCodeEntity entity : entities) {
            ReportTypeCode code = modelMapper.map(entity, ReportTypeCode.class);
            codes.add(code);
        }
        return codes;
    }

    public ReportTypeCode transformToDTO (ReportTypeCodeEntity entity ) {
        ReportTypeCode code = modelMapper.map(entity, ReportTypeCode.class);
        return code;
    }
}
