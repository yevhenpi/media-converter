package ua.pidopryhora.mediaconverter.requestmanager.validation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.IdempotencyService;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class IdempotencyValidator<T extends AuthenticatedRequestDTO> implements Validator<T> {

    private final IdempotencyService idempotencyService;

    private static final Map<Class<? extends AuthenticatedRequestDTO>, String> messageMap = new HashMap<>();


    static {
        messageMap.put(UploadRequestDTO.class, "URL for this file is already created and not expired yet");
        messageMap.put(AudioJobRequestDTO.class, "Request is already being processed");
        // Add more mappings as needed
    }


    @Override
    public void validate(T requestDTO) throws ValidationException {
        if(!idempotencyService.addIdempotencyKey(requestDTO)){
            String message = messageMap.getOrDefault(requestDTO.getClass(),
                    "Duplicate request");
            throw new ValidationException(message, HttpStatus.CONFLICT);
       }
    }
}
