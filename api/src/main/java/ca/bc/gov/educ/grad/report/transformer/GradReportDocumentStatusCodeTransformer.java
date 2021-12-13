package ca.bc.gov.educ.grad.report.transformer;

import ca.bc.gov.educ.grad.report.dto.DocumentStatusCode;
import ca.bc.gov.educ.grad.report.entity.DocumentStatusCodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradReportDocumentStatusCodeTransformer {

    @Autowired
    ModelMapper modelMapper;

    public List<DocumentStatusCode> transformToDTO (List<DocumentStatusCodeEntity> entities ) {
        List<DocumentStatusCode> codes = new ArrayList<DocumentStatusCode>();
        for (DocumentStatusCodeEntity entity : entities) {
            DocumentStatusCode code = modelMapper.map(entity, DocumentStatusCode.class);
            codes.add(code);
        }
        return codes;
    }

    public DocumentStatusCode transformToDTO (DocumentStatusCodeEntity entity ) {
        DocumentStatusCode code = modelMapper.map(entity, DocumentStatusCode.class);
        return code;
    }
}
