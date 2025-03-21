package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioConversionRequestDTO;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq.UpdateProducer;
import ua.pidopryhora.mediaconverter.requestmanager.service.JobFactory;
import ua.pidopryhora.mediaconverter.requestmanager.service.ValidationService;

import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AudioConversionRequestProcessorTest {

    @Mock
    private UpdateProducer updateProducer;

    @Mock
    private JobDataService jobDataService;

    @Mock
    private JobFactory<AudioConversionRequestDTO, AudioJobDTO> jobFactory;

    @Mock
    private ValidationService<AudioConversionRequestDTO> validationService;

    @InjectMocks
    private AudioConversionRequestProcessor audioConversionRequestProcessor;



    @Test
    void testProcessRequest_Success() {
        // Arrange
        AudioConversionRequestDTO requestDTO = mock(AudioConversionRequestDTO.class);
        String jobId = "1";
        AudioJobDTO job = AudioJobDTO.builder().jobId(jobId).build();
        when(jobFactory.createJob(requestDTO)).thenReturn(job);
        doNothing().when(validationService).validate(requestDTO); // Simulate successful validation

        // Act
        ResponseEntity<?> response = audioConversionRequestProcessor.processRequest(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("conversion is started", responseBody.get("message"));
        assertEquals(jobId, responseBody.get("jobId"));

        verify(validationService).validate(requestDTO); // Ensure validation was called
        verify(jobFactory).createJob(requestDTO); // Ensure job creation
        verify(updateProducer).produce(eq(AUDIO_CONVERSION_QUEUE), eq(job)); // Ensure job was produced to the queue
        verify(jobDataService).saveData(job); // Ensure job data was saved
    }

    @Test
    void testProcessRequest_ValidationFailure() {
        // Arrange
        AudioConversionRequestDTO requestDTO = mock(AudioConversionRequestDTO.class);
        doThrow(new ValidationException("Validation failed", HttpStatus.BAD_REQUEST)).when(validationService).validate(requestDTO); // Simulate validation failure

        // Act & Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> audioConversionRequestProcessor.processRequest(requestDTO));
        assertEquals("Validation failed", exception.getMessage());

        // Verify that no further method calls are made if validation fails
        verify(validationService).validate(requestDTO);
        verifyNoInteractions(jobFactory, updateProducer, jobDataService); // Ensure no further interactions
    }

    // Optional additional test for failure in job creation, queue production, or job data saving

    @Test
    void testProcessRequest_JobCreationFailure() {
        // Arrange
        AudioConversionRequestDTO requestDTO = mock(AudioConversionRequestDTO.class);
        String jobId = "1";
        doNothing().when(validationService).validate(requestDTO); // Simulate successful validation
        when(jobFactory.createJob(requestDTO)).thenThrow(new RuntimeException("Job creation failed"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> audioConversionRequestProcessor.processRequest(requestDTO));
        assertEquals("Job creation failed", exception.getMessage());

        // Verify that no further method calls are made if job creation fails
        verify(validationService).validate(requestDTO);
        verify(jobFactory).createJob(requestDTO);
        verifyNoInteractions(updateProducer, jobDataService); // Ensure no further method calls
    }

    @Test
    void testProcessRequest_QueueProductionFailure() {
        // Arrange
        AudioConversionRequestDTO requestDTO = mock(AudioConversionRequestDTO.class);
        String jobId = "1";
        AudioJobDTO job = mock(AudioJobDTO.class);
        doNothing().when(validationService).validate(requestDTO); // Simulate successful validation
        when(jobFactory.createJob(requestDTO)).thenReturn(job);
        doNothing().when(updateProducer).produce(eq(AUDIO_CONVERSION_QUEUE), eq(job));
        doThrow(new RuntimeException("Queue production failed")).when(updateProducer).produce(eq(AUDIO_CONVERSION_QUEUE), eq(job)); // Simulate queue production failure

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> audioConversionRequestProcessor.processRequest(requestDTO));
        assertEquals("Queue production failed", exception.getMessage());

        // Verify that no further method calls are made if queue production fails
        verify(validationService).validate(requestDTO);
        verify(jobFactory).createJob(requestDTO);
        verify(updateProducer).produce(eq(AUDIO_CONVERSION_QUEUE), eq(job));
        verifyNoInteractions(jobDataService); // Ensure job data saving wasn't attempted
    }

    @Test
    void testProcessRequest_JobDataSavingFailure() {
        // Arrange
        AudioConversionRequestDTO requestDTO = mock(AudioConversionRequestDTO.class);
        String jobId = "1";
        AudioJobDTO job = mock(AudioJobDTO.class);
        doNothing().when(validationService).validate(requestDTO); // Simulate successful validation
        when(jobFactory.createJob(requestDTO)).thenReturn(job);
        doNothing().when(updateProducer).produce(eq(AUDIO_CONVERSION_QUEUE), eq(job));
        doThrow(new RuntimeException("Job data saving failed")).when(jobDataService).saveData(job); // Simulate job data saving failure

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> audioConversionRequestProcessor.processRequest(requestDTO));
        assertEquals("Job data saving failed", exception.getMessage());

        // Verify that all other methods were called and that job data saving failed
        verify(validationService).validate(requestDTO);
        verify(jobFactory).createJob(requestDTO);
        verify(updateProducer).produce(eq(AUDIO_CONVERSION_QUEUE), eq(job));
        verify(jobDataService).saveData(job);
    }
}
