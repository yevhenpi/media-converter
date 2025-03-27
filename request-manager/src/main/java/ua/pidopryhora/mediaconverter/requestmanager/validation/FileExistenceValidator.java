package ua.pidopryhora.mediaconverter.requestmanager.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.JobRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.FileDataService;

@Component
@RequiredArgsConstructor
public class FileExistenceValidator<T extends JobRequestDTO> implements Validator<T> {

    private final FileDataService fileDataService;

    @Override
    public void validate(T request) throws ValidationException {
        if(!fileDataService.isPresent(request.getFileName(), request.getUserId()))
            throw new ValidationException("File is not found", HttpStatus.NOT_FOUND);
    }
}
