package ua.pidopryhora.mediaconverter.requestmanager.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.JobRequestDTO;

import static ua.pidopryhora.mediaconverter.common.model.JobStatus.DONE;

@Component
@RequiredArgsConstructor
public class JobStatusValidator implements Validator<DownloadRequestDTO>{

    private final JobDataService jobDataService;

    @Override
    public void validate(DownloadRequestDTO requestDTO) throws ValidationException {
        if(!jobDataService.getJob(requestDTO.getJobId()).getJobStatus().equals(String.valueOf(DONE)))
            throw new ValidationException("Job is not done yet, try later" , HttpStatus.ACCEPTED);

    }
}
