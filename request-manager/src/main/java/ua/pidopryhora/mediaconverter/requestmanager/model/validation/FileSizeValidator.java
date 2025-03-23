package ua.pidopryhora.mediaconverter.requestmanager.model.validation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UserLimitsProvider;
import ua.pidopryhora.mediaconverter.requestmanager.model.UserRole;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;

@Component
@RequiredArgsConstructor
public class FileSizeValidator implements Validator<UploadRequestDTO> {

    private final UserLimitsProvider limitsProvider;

    @Override
    public void validate(UploadRequestDTO requestDTO) throws ValidationException {

        long maxFileSize = limitsProvider.getLimitsByRole(UserRole.valueOf(requestDTO.getRole())).maxFileSize();

        if(requestDTO.getFileSize() > maxFileSize ) throw new ValidationException("You exceeded max file size for your plan", HttpStatus.FORBIDDEN);

    }
}
