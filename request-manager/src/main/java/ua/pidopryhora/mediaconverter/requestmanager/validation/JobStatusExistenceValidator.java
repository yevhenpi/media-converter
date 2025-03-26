package ua.pidopryhora.mediaconverter.requestmanager.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.DownloadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.StatusRequestDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobStatusExistenceValidator implements Validator<StatusRequestDTO> {

    private final JobDataService jobDataService;

    @Override
    public void validate(StatusRequestDTO requestDTO) throws ValidationException {

        parseIds(requestDTO.getJobIds());


    }

    private void parseIds(List<String> jobIds){
        for(String id:jobIds) {
            if (!jobDataService.isPresent(id))
                throw new ValidationException("Job is not found", HttpStatus.NOT_FOUND);
        }
    }
}
