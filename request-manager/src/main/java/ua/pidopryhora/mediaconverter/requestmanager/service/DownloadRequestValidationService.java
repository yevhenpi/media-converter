package ua.pidopryhora.mediaconverter.requestmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.validation.JobExistenceValidator;
import ua.pidopryhora.mediaconverter.requestmanager.validation.JobStatusValidator;

@Component
@RequiredArgsConstructor
public class DownloadRequestValidationService implements RequestValidationService<DownloadRequestDTO> {

    private final JobExistenceValidator jobExistenceValidator;
    private final JobStatusValidator statusValidator;

    @Override
    public void validateRequest(DownloadRequestDTO requestDTO) {
        jobExistenceValidator.validate(requestDTO);
        statusValidator.validate(requestDTO);


    }
}
