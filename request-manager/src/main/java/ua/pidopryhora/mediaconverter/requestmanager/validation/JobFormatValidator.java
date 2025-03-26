package ua.pidopryhora.mediaconverter.requestmanager.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.jave2.JAVEDataSupplier;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.JobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.VideoJobRequestDTO;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JobFormatValidator<T extends JobRequestDTO> implements Validator<T>{

    private final JAVEDataSupplier javeDataSupplier;

    @Override
    public void validate(T requestDTO) throws ValidationException {
        if(requestDTO instanceof AudioJobRequestDTO) validateAudioJob(requestDTO);
        if(requestDTO instanceof VideoJobRequestDTO) validateVideoJob(requestDTO);

    }

    private void validateAudioJob(T requestDTO){
        if (!javeDataSupplier.getAudioFormats().contains(requestDTO.getOutputFormat()))
            throw  new ValidationException("Format is not supported", HttpStatus.BAD_REQUEST);

    }
    private void validateVideoJob(T requestDTO){
        if (!javeDataSupplier.getVideoFormats().contains(requestDTO.getOutputFormat()))
            throw  new ValidationException("Format is not supported", HttpStatus.BAD_REQUEST);

    }
}
