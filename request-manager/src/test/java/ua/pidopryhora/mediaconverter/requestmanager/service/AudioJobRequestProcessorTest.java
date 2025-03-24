package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq.UpdateProducer;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AudioJobRequestProcessorTest {

    @Mock
    private UpdateProducer updateProducer;

    @Mock
    private JobDataService jobDataService;

    @Mock
    private JobFactory<AudioJobRequestDTO, AudioJobDTO> jobFactory;

    @Mock
    private RequestValidationService<AudioJobRequestDTO> uploadRequestValidationService;

    @InjectMocks
    private AudioJobRequestProcessor audioJobRequestProcessor;

    private AudioJobRequestDTO requestDTO;
    private AudioJobDTO job;

    @BeforeEach
    void setUp() {
        requestDTO = mock(AudioJobRequestDTO.class);
        job = AudioJobDTO.builder().jobId("1").build();
        when(jobFactory.createJob(requestDTO)).thenReturn(job);
    }

    @Test
    void testProcessRequest_Success() {
        ArgumentCaptor<AudioJobDTO> jobCaptor = ArgumentCaptor.forClass(AudioJobDTO.class);
        doNothing().when(uploadRequestValidationService).validate(requestDTO);

        ResponseEntity<?> response = audioJobRequestProcessor.processRequest(requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assert responseBody != null;
        assertEquals("conversion is started", responseBody.get("message"));
        assertEquals("1", responseBody.get("jobId"));

        verify(uploadRequestValidationService).validate(requestDTO);
        verify(jobFactory).createJob(requestDTO);
        verify(updateProducer).produce(eq(AUDIO_CONVERSION_QUEUE), jobCaptor.capture());
        assertEquals(job, jobCaptor.getValue());
        verify(jobDataService).saveData(job);
    }

    @Test
    void testProcessRequest_ValidationFailure() {
        doThrow(new ValidationException("Validation failed", HttpStatus.BAD_REQUEST)).when(uploadRequestValidationService).validate(requestDTO);

        // Act & Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> audioJobRequestProcessor.processRequest(requestDTO));
        assertEquals("Validation failed", exception.getMessage());

        verify(uploadRequestValidationService).validate(requestDTO);
        verifyNoInteractions(jobFactory, updateProducer, jobDataService);
    }

}
