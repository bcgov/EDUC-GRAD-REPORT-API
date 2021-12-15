package ca.bc.gov.educ.grad.report.transformer;

import ca.bc.gov.educ.grad.report.dto.GradReportSignatureImage;
import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GradReportSignatureTransformer {

    @Autowired
    ModelMapper modelMapper;

    public GradReportSignatureImage transformToDTO (GradReportSignatureImageEntity GradReportSignatureImageEntity) {
    	GradReportSignatureImage GradReportSignatureImage = modelMapper.map(GradReportSignatureImageEntity, GradReportSignatureImage.class);
        return GradReportSignatureImage;
    }

    public GradReportSignatureImage transformToDTO (Optional<GradReportSignatureImageEntity> GragReportSignatureImageEntity ) {
    	GradReportSignatureImageEntity cae = new GradReportSignatureImageEntity();
        if (GragReportSignatureImageEntity.isPresent())
            cae = GragReportSignatureImageEntity.get();

        GradReportSignatureImage GradReportSignatureImage = modelMapper.map(cae, GradReportSignatureImage.class);
        return GradReportSignatureImage;
    }

	public List<GradReportSignatureImage> transformToDTO (List<GradReportSignatureImageEntity> GragReportSignatureImageEntities ) {
		List<GradReportSignatureImage> gradReportSignatureImageList = new ArrayList<GradReportSignatureImage>();
        for (GradReportSignatureImageEntity GradReportSignatureImageEntity : GragReportSignatureImageEntities) {
        	GradReportSignatureImage GradReportSignatureImage = new GradReportSignatureImage();
        	GradReportSignatureImage = modelMapper.map(GradReportSignatureImageEntity, GradReportSignatureImage.class);
        	gradReportSignatureImageList.add(GradReportSignatureImage);
        }
        return gradReportSignatureImageList;
    }

    public GradReportSignatureImageEntity transformToEntity(GradReportSignatureImage GradReportSignatureImage) {
        GradReportSignatureImageEntity GradReportSignatureImageEntity = modelMapper.map(GradReportSignatureImage, GradReportSignatureImageEntity.class);
        return GradReportSignatureImageEntity;
    }
}
