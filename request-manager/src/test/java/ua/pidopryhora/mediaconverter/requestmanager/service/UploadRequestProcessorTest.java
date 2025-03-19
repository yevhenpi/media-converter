package ua.pidopryhora.mediaconverter.requestmanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.s3.PresignedUrlService;

@ExtendWith(MockitoExtension.class)
class UploadRequestProcessorTest {

    @Mock
    private PresignedUrlService presignedUrlService;

    @Mock
    private UploadRequestCachingService uploadRequestCachingService;

    @InjectMocks
    private UploadRequestProcessor uploadRequestProcessor;

    @Test
    void processRequest_shouldReturnPresignedUrl() throws Exception {
        // given
        UploadRequestDTO requestDTO = new UploadRequestDTO();

        URL expectedUrl = new URL("http://example.com/presigned");
        when(presignedUrlService.generatePresignedUrl(requestDTO)).thenReturn(expectedUrl);

        // when
        ResponseEntity<?> response = uploadRequestProcessor.processRequest(requestDTO);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertThat(body).containsEntry("url", expectedUrl.toString());


        // Verify that the caching service is called.
        verify(uploadRequestCachingService).cacheData(requestDTO);
    }
}
