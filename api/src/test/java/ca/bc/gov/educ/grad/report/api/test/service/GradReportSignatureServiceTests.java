package ca.bc.gov.educ.grad.report.api.test.service;


import ca.bc.gov.educ.grad.report.api.service.RESTService;
import ca.bc.gov.educ.grad.report.dao.ReportRequestDataThreadLocal;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
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
        GradReportSignatureImage dto = validSignatureImageToSave();
        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();

        when(gradReportSignatureTransformer.transformToEntity(dto)).thenReturn(entity);
        when(signatureImageRepository.save(entity)).thenReturn(entity);
        when(gradReportSignatureTransformer.transformToDTO(entity)).thenReturn(dto);

        GradReportSignatureImage result = gradReportSignatureService.saveSignatureImage(dto);
        assertNotNull(result);
    }

    @Test
    void testSaveSignatureImage_updateExisting_savesImageNameOrganizationAndUpdatedBy() {
        UUID id = UUID.randomUUID();
        GradReportSignatureImage dto = validSignatureImageToSave();
        dto.setSignatureId(id);

        GradReportSignatureImageEntity entity = new GradReportSignatureImageEntity();
        entity.setSignatureId(id);

        GradReportSignatureImageEntity existing = new GradReportSignatureImageEntity();
        existing.setSignatureId(id);
        existing.setGradReportSignatureCode("083");
        existing.setGradReportSignatureName("Old Superintendent");
        existing.setGradReportSignatureOrganizationName("Old Organization");
        existing.setUpdatedBy("GRAD_REPORT_API");

        when(gradReportSignatureTransformer.transformToEntity(dto)).thenReturn(entity);
        when(signatureImageRepository.findById(id)).thenReturn(Optional.of(existing));
        when(signatureImageRepository.save(any())).thenReturn(existing);
        when(gradReportSignatureTransformer.transformToDTO(existing)).thenReturn(dto);

        ReportRequestDataThreadLocal.setCurrentUser("ACOORDINATOR");
        try {
            GradReportSignatureImage result = gradReportSignatureService.saveSignatureImage(dto);
            assertNotNull(result);
        } finally {
            ReportRequestDataThreadLocal.setCurrentUser(null);
        }

        assertArrayEquals(dto.getSignatureContent(), existing.getSignatureContent());
        assertEquals("New Superintendent", existing.getGradReportSignatureName());
        assertEquals("K̓wsaltktnéws ne Secwepemcúl'ecw", existing.getGradReportSignatureOrganizationName());
        assertEquals("083", existing.getGradReportSignatureCode());
        assertEquals("ACOORDINATOR", existing.getUpdatedBy());
    }

    @Test
    void testSaveSignatureImage_rejectsNonPngImage() {
        GradReportSignatureImage dto = validSignatureImageToSave();
        dto.setSignatureContent(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0, 0, 0, 0, 0});

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> gradReportSignatureService.saveSignatureImage(dto));
        assertEquals("Signature image must be a valid PNG file", exception.getMessage());
        verify(signatureImageRepository, never()).save(any());
    }

    @Test
    void testSaveSignatureImage_rejectsCorruptPngImage() {
        GradReportSignatureImage dto = validSignatureImageToSave();
        dto.setSignatureContent(new byte[]{(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n', 0, 0});

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> gradReportSignatureService.saveSignatureImage(dto));
        assertEquals("Signature image must be a valid PNG file", exception.getMessage());
        verify(signatureImageRepository, never()).save(any());
    }

    @Test
    void testSaveSignatureImage_rejectsImageOverMaxFileSize() {
        GradReportSignatureImage dto = validSignatureImageToSave();
        dto.setSignatureContent(Arrays.copyOf(dto.getSignatureContent(), 1024 * 1024 + 1));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> gradReportSignatureService.saveSignatureImage(dto));
        assertEquals("Signature image is too large: maximum is 1 MB", exception.getMessage());
        verify(signatureImageRepository, never()).save(any());
    }

    @Test
    void testSaveSignatureImage_rejectsImageOverMaxDimensions() {
        GradReportSignatureImage dto = validSignatureImageToSave();
        dto.setSignatureContent(pngImage(3001, 1));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> gradReportSignatureService.saveSignatureImage(dto));
        assertEquals("Signature image is too large: 3001x1 pixels, maximum is 3000x3000", exception.getMessage());
        verify(signatureImageRepository, never()).save(any());
    }

    @Test
    void testSaveSignatureImage_rejectsBlankOrganizationName() {
        GradReportSignatureImage dto = validSignatureImageToSave();
        dto.setGradReportSignatureOrganizationName(" ");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> gradReportSignatureService.saveSignatureImage(dto));
        assertEquals("Organization Name is required", exception.getMessage());
        verify(signatureImageRepository, never()).save(any());
    }

    @Test
    void testSaveSignatureImage_rejectsOrganizationNameOverColumnByteLimit() {
        GradReportSignatureImage dto = validSignatureImageToSave();
        // 40 characters but 80 bytes in UTF-8, so it would not fit the VARCHAR2(64 BYTE) column
        dto.setGradReportSignatureOrganizationName("é".repeat(40));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> gradReportSignatureService.saveSignatureImage(dto));
        assertTrue(exception.getMessage().startsWith("Organization Name is too long: 80 bytes, maximum is 64"));
        verify(signatureImageRepository, never()).save(any());
    }

    private static GradReportSignatureImage validSignatureImageToSave() {
        GradReportSignatureImage signatureImage = new GradReportSignatureImage();
        signatureImage.setGradReportSignatureCode("083");
        signatureImage.setSignatureContent(pngImage(1, 1));
        signatureImage.setGradReportSignatureName("New Superintendent");
        signatureImage.setGradReportSignatureOrganizationName("K̓wsaltktnéws ne Secwepemcúl'ecw");
        return signatureImage;
    }

    private static byte[] pngImage(int width, int height) {
        try {
            ByteArrayOutputStream pngBytes = new ByteArrayOutputStream();
            ImageIO.write(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB), "png", pngBytes);
            return pngBytes.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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
