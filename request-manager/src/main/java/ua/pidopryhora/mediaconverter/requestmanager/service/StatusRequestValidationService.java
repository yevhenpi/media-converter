package ua.pidopryhora.mediaconverter.requestmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import ua.pidopryhora.mediaconverter.requestmanager.model.StatusRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.validation.JobExistenceValidator;
import ua.pidopryhora.mediaconverter.requestmanager.validation.JobStatusExistenceValidator;

@Service
@RequiredArgsConstructor
public class StatusRequestValidationService implements RequestValidationService<StatusRequestDTO> {

    private final JobStatusExistenceValidator jobExistenceValidator;

    @Override
    public void validateRequest(StatusRequestDTO requestDTO) {

        jobExistenceValidator.validate(requestDTO);

    }


}
