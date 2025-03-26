package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.s3.S3PresignedUrlService;

import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class UploadRequestProcessorTest {

    @Mock
    private S3PresignedUrlService presignedUrlService;

    @Mock
    private UploadRequestCachingService uploadRequestCachingService;

    @Mock
    private RequestValidationService<UploadRequestDTO> uploadRequestValidationService;

    @InjectMocks
    private UploadRequestProcessor uploadRequestProcessor;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testProcessRequest_Success() throws Exception {
        // Arrange
        UploadRequestDTO requestDTO = mock(UploadRequestDTO.class);
        URL presignedUrl = new URL("https://example.com/presigned-url");
        doNothing().when(uploadRequestValidationService).validateRequest(requestDTO);  // No validation errors
        when(presignedUrlService.generatePresignedUrl(requestDTO)).thenReturn(presignedUrl);
        doNothing().when(uploadRequestCachingService).cacheData(requestDTO);

        // Act
        ResponseEntity<?> response = uploadRequestProcessor.processRequest(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals(presignedUrl.toString(), responseBody.get("url"));
        verify(uploadRequestValidationService).validateRequest(requestDTO);
        verify(presignedUrlService).generatePresignedUrl(requestDTO);
        verify(uploadRequestCachingService).cacheData(requestDTO);
    }

    @Test
    void testProcessRequest_ValidationFailure() throws Exception {
        // Arrange
        UploadRequestDTO requestDTO = mock(UploadRequestDTO.class);
        // Simulate validation failure by throwing a ValidationException
        doThrow(new ValidationException("Validation failed", HttpStatus.BAD_REQUEST))
                .when(uploadRequestValidationService).validateRequest(requestDTO);

        // Act & Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> uploadRequestProcessor.processRequest(requestDTO));
        assertEquals("Validation failed", exception.getMessage());
        verify(uploadRequestValidationService).validateRequest(requestDTO); // Ensure validation was called
        verifyNoInteractions(presignedUrlService, uploadRequestCachingService); // Ensure no further method calls
    }

    @Test
    void testProcessRequest_GeneratePresignedUrlFailure() throws Exception {
        // Arrange
        UploadRequestDTO requestDTO = mock(UploadRequestDTO.class);
        doNothing().when(uploadRequestValidationService).validateRequest(requestDTO);  // Assuming validation passes
        when(presignedUrlService.generatePresignedUrl(requestDTO)).thenThrow(new RuntimeException("Error generating presigned URL"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> uploadRequestProcessor.processRequest(requestDTO));
        assertEquals("Error generating presigned URL", exception.getMessage());
        verify(uploadRequestValidationService).validateRequest(requestDTO);  // Ensure validation was called
        verify(presignedUrlService).generatePresignedUrl(requestDTO);  // Ensure URL generation was attempted
        verifyNoInteractions(uploadRequestCachingService);  // Ensure caching was not called
    }

    @Test
    void testProcessRequest_CachingFailure() throws Exception {
        // Arrange
        UploadRequestDTO requestDTO = mock(UploadRequestDTO.class);
        URL presignedUrl = new URL("https://example.com/presigned-url");
        doNothing().when(uploadRequestValidationService).validateRequest(requestDTO);  // Assuming validation passes
        when(presignedUrlService.generatePresignedUrl(requestDTO)).thenReturn(presignedUrl);
        doThrow(new RuntimeException("Error caching data")).when(uploadRequestCachingService).cacheData(requestDTO);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> uploadRequestProcessor.processRequest(requestDTO));
        assertEquals("Error caching data", exception.getMessage());
        verify(uploadRequestValidationService).validateRequest(requestDTO);  // Ensure validation was called
        verify(presignedUrlService).generatePresignedUrl(requestDTO);  // Ensure URL generation was attempted
        verify(uploadRequestCachingService).cacheData(requestDTO);  // Ensure caching was attempted
    }
}