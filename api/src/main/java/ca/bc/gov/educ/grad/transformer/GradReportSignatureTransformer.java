package ca.bc.gov.educ.grad.transformer;

import ca.bc.gov.educ.grad.dto.GragReportSignatureImage;
import ca.bc.gov.educ.grad.entity.GragReportSignatureImageEntity;
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

    public GragReportSignatureImage transformToDTO (GragReportSignatureImageEntity GragReportSignatureImageEntity) {
    	GragReportSignatureImage GragReportSignatureImage = modelMapper.map(GragReportSignatureImageEntity, GragReportSignatureImage.class);
        return GragReportSignatureImage;
    }

    public GragReportSignatureImage transformToDTO ( Optional<GragReportSignatureImageEntity> GragReportSignatureImageEntity ) {
    	GragReportSignatureImageEntity cae = new GragReportSignatureImageEntity();
        if (GragReportSignatureImageEntity.isPresent())
            cae = GragReportSignatureImageEntity.get();

        GragReportSignatureImage GragReportSignatureImage = modelMapper.map(cae, GragReportSignatureImage.class);
        return GragReportSignatureImage;
    }

	public List<GragReportSignatureImage> transformToDTO (List<GragReportSignatureImageEntity> GragReportSignatureImageEntities ) {
		List<GragReportSignatureImage> GragReportSignatureImageList = new ArrayList<GragReportSignatureImage>();
        for (GragReportSignatureImageEntity GragReportSignatureImageEntity : GragReportSignatureImageEntities) {
        	GragReportSignatureImage GragReportSignatureImage = new GragReportSignatureImage();
        	GragReportSignatureImage = modelMapper.map(GragReportSignatureImageEntity, GragReportSignatureImage.class);            
        	GragReportSignatureImageList.add(GragReportSignatureImage);
        }
        return GragReportSignatureImageList;
    }

    public GragReportSignatureImageEntity transformToEntity(GragReportSignatureImage GragReportSignatureImage) {
        GragReportSignatureImageEntity GragReportSignatureImageEntity = modelMapper.map(GragReportSignatureImage, GragReportSignatureImageEntity.class);
        return GragReportSignatureImageEntity;
    }
}
