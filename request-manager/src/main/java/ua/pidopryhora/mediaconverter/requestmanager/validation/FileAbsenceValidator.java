package ua.pidopryhora.mediaconverter.requestmanager.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.FileDataService;
@Component
@RequiredArgsConstructor
public class FileAbsenceValidator implements Validator<UploadRequestDTO> {

    private final FileDataService fileDataService;

    @Override
    public void validate(UploadRequestDTO request) throws ValidationException {
        if(fileDataService.isPresent(request.getFileName(), request.getUserId()))
            throw new ValidationException("File is already uploaded", HttpStatus.CONFLICT);

    }
}
