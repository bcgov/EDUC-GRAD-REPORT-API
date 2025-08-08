package ca.bc.gov.educ.grad.report.api.test.service;


import ca.bc.gov.educ.grad.report.api.service.RESTService;
import ca.bc.gov.educ.grad.report.dao.SignatureImageRepository;
import ca.bc.gov.educ.grad.report.dto.GradReportSignatureImage;
import ca.bc.gov.educ.grad.report.dto.impl.DistrictImpl;
import ca.bc.gov.educ.grad.report.entity.GradReportSignatureImageEntity;
import ca.bc.gov.educ.grad.report.service.GradReportSignatureService;
import ca.bc.gov.educ.grad.report.transformer.GradReportSignatureTransformer;
import ca.bc.gov.educ.grad.report.utils.EducGradReportApiConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class GradReportSignatureServiceTests {

    @Mock
    private SignatureImageRepository signatureImageRepository;

    @Mock
    private GradReportSignatureTransformer gradReportSignatureTransformer;

    @Mock
    private WebClient webClient;

    @Mock
    private EducGradReportApiConstants constants;

    @Mock
    private RESTService restService;

    @InjectMocks
    private GradReportSignatureService gradReportSignatureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSignatureImageBySignatureId_found() {
        UUID id = UUID.randomUUID();
        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();
        entity.setSignatureId(id);

        when(signatureImageRepository.findById(id)).thenReturn(Optional.of(entity));
        GradReportSignatureImage dto = new GradReportSignatureImage();
        when(gradReportSignatureTransformer.transformToDTO(Optional.ofNullable(any()))).thenReturn(dto);

        GradReportSignatureImage result = gradReportSignatureService.getSignatureImageBySignatureId(id.toString());
        assertNotNull(result);
    }

    @Test
    void testGetSignatureImageByCode_found() {
        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();
        entity.setGradReportSignatureCode("SIGN001");

        when(signatureImageRepository.findBySignatureCode("SIGN001")).thenReturn(entity);
        when(gradReportSignatureTransformer.transformToDTO(entity)).thenReturn(new GradReportSignatureImage());

        GradReportSignatureImage result = gradReportSignatureService.getSignatureImageByCode("SIGN001");
        assertNotNull(result);
    }

    @Test
    void testSaveSignatureImage_insertNew() {
        GradReportSignatureImage dto = new GradReportSignatureImage();
        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();

        when(gradReportSignatureTransformer.transformToEntity(dto)).thenReturn(entity);
        when(signatureImageRepository.save(entity)).thenReturn(entity);
        when(gradReportSignatureTransformer.transformToDTO(entity)).thenReturn(dto);

        GradReportSignatureImage result = gradReportSignatureService.saveSignatureImage(dto);
        assertNotNull(result);
    }

    @Test
    void testSaveSignatureImage_updateExisting() {
        UUID id = UUID.randomUUID();
        GradReportSignatureImage dto = new GradReportSignatureImage();
        byte[] content = new byte[]{1, 2, 3};
        dto.setSignatureContent(content);

        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();
        entity.setSignatureId(id);

        GradReportSignatureImageEntity existing = new GradReportSignatureImageEntity();
        existing.setSignatureId(id);

        when(gradReportSignatureTransformer.transformToEntity(dto)).thenReturn(entity);
        when(signatureImageRepository.findById(id)).thenReturn(Optional.of(existing));
        when(signatureImageRepository.save(any())).thenReturn(existing);
        when(gradReportSignatureTransformer.transformToDTO(existing)).thenReturn(dto);

        entity.setSignatureId(id);
        GradReportSignatureImage result = gradReportSignatureService.saveSignatureImage(dto);
        assertNotNull(result);
    }

    @Test
    void testGetSignatureImages_emptyList() {
        when(signatureImageRepository.findAll()).thenReturn(Collections.emptyList());
        List<GradReportSignatureImage> result = gradReportSignatureService.getSignatureImages();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetSignatureImageWithDistInfoByCode() {
        DistrictImpl distImpl = new ca.bc.gov.educ.grad.report.dto.impl.DistrictImpl();
        distImpl.setDistno("123");
        distImpl.setName("My District");
        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();
        entity.setGradReportSignatureCode("123");

        GradReportSignatureImage dto = new GradReportSignatureImage();
        dto.setGradReportSignatureCode("123");

        when(signatureImageRepository.findBySignatureCode("123")).thenReturn(entity);
        when(gradReportSignatureTransformer.transformToDTO(entity)).thenReturn(dto);
        when(constants.getDistrictDetails()).thenReturn("http://example.com/%s");
        when(restService.get(anyString(), eq(ca.bc.gov.educ.grad.report.dto.impl.DistrictImpl.class), eq(webClient)))
                .thenReturn(distImpl);

        GradReportSignatureImage result = gradReportSignatureService.getSignatureImageWithDistInfoByCode("123");
        assertNotNull(result);
        assertEquals("My District", result.getDistrictName());
    }

    @Test
    void testGetSignatureImages_singleRecord_withDistrictInfo() {
        DistrictImpl distImpl = new ca.bc.gov.educ.grad.report.dto.impl.DistrictImpl();
        distImpl.setDistno("123");
        distImpl.setName("My District");

        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();
        entity.setGradReportSignatureCode("456");

        GradReportSignatureImage dto = new GradReportSignatureImage();
        dto.setGradReportSignatureCode("456");

        List<GradReportSignatureImageEntity> entities = List.of(entity);

        when(signatureImageRepository.findAll()).thenReturn(entities);
        when(gradReportSignatureTransformer.transformToDTO(entity)).thenReturn(dto);
        when(constants.getDistrictDetails()).thenReturn("http://example.com/%s");
        when(restService.get(anyString(), eq(ca.bc.gov.educ.grad.report.dto.impl.DistrictImpl.class), eq(webClient)))
                .thenReturn(distImpl);

        List<GradReportSignatureImage> result = gradReportSignatureService.getSignatureImages();
        assertEquals(1, result.size());
        assertEquals("My District", result.get(0).getDistrictName());
    }

    @Test
    void testGetSignatureImages_districtServiceThrowsException() {
        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();
        entity.setGradReportSignatureCode("789");

        GradReportSignatureImage dto = new GradReportSignatureImage();
        dto.setGradReportSignatureCode("789");

        List<GradReportSignatureImageEntity> entities = List.of(entity);

        when(signatureImageRepository.findAll()).thenReturn(entities);
        when(gradReportSignatureTransformer.transformToDTO(entity)).thenReturn(dto);
        when(constants.getDistrictDetails()).thenReturn("http://example.com/%s");
        when(restService.get(anyString(), eq(ca.bc.gov.educ.grad.report.dto.impl.DistrictImpl.class), eq(webClient)))
                .thenThrow(new RuntimeException("Connection error"));

        List<GradReportSignatureImage> result = gradReportSignatureService.getSignatureImages();
        assertEquals(1, result.size());
        assertNull(result.get(0).getDistrictName());
    }

}
