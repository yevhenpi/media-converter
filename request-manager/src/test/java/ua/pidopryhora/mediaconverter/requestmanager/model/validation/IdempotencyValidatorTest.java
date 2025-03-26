package ua.pidopryhora.mediaconverter.requestmanager.model.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.IdempotencyService;
import ua.pidopryhora.mediaconverter.requestmanager.validation.IdempotencyValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IdempotencyValidatorTest {

    @Mock
    private IdempotencyService idempotencyService;

    @InjectMocks
    private IdempotencyValidator<RequestDTO> idempotencyValidator;


    @Test
    void testValidate_Success() {

        RequestDTO requestDTO = mock(UploadRequestDTO.class);
        when(idempotencyService.addIdempotencyKey(requestDTO)).thenReturn(true);


        assertDoesNotThrow(() -> idempotencyValidator.validate(requestDTO));
        verify(idempotencyService).addIdempotencyKey(requestDTO);
    }

    @Test
    void testValidate_Failure() {

        RequestDTO requestDTO = mock(UploadRequestDTO.class);
        when(idempotencyService.addIdempotencyKey(requestDTO)).thenReturn(false);


        ValidationException exception = assertThrows(ValidationException.class, () -> idempotencyValidator.validate(requestDTO));
        assertEquals("URL for this file is already created and not expired yet", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    void testValidate_Failure_WithGenericMessage() {

        RequestDTO requestDTO = mock(RequestDTO.class); // Using a generic RequestDTO not in the map
        when(idempotencyService.addIdempotencyKey(requestDTO)).thenReturn(false);


        ValidationException exception = assertThrows(ValidationException.class, () -> idempotencyValidator.validate(requestDTO));
        assertEquals("Duplicate request", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    void testValidate_Failure_AudioConversionRequestDTO() {

        RequestDTO requestDTO = mock(AudioJobRequestDTO.class);
        when(idempotencyService.addIdempotencyKey(requestDTO)).thenReturn(false);


        ValidationException exception = assertThrows(ValidationException.class, () -> idempotencyValidator.validate(requestDTO));
        assertEquals("Request is already being processed", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }
}
