package ua.pidopryhora.mediaconverter.requestmanager.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.JobRequestDTO;
@Slf4j
@Component
@RequiredArgsConstructor
public class JobExistenceValidator implements Validator<DownloadRequestDTO> {

    private final JobDataService jobDataService;
    @Override
    public void validate(DownloadRequestDTO requestDTO) throws ValidationException {
        if(!jobDataService.isPresent(requestDTO.getJobId()))
            throw new ValidationException("Job is not found", HttpStatus.NOT_FOUND);


    }
}
