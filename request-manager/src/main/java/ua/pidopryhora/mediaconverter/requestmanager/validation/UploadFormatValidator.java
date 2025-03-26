package ua.pidopryhora.mediaconverter.requestmanager.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.jave2.JAVEDataSupplier;
import ua.pidopryhora.mediaconverter.requestmanager.exception.ValidationException;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.UploadRequestDTO;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UploadFormatValidator implements Validator<UploadRequestDTO>{

    private final JAVEDataSupplier javeDataSupplier;


    @Override
    public void validate(UploadRequestDTO requestDTO) throws ValidationException {
        var fileFormat = extractFormatFromName(requestDTO.getFileName());

        if (!javeDataSupplier.getAllFormats().contains(fileFormat))
            throw  new ValidationException("Format is not supported", HttpStatus.BAD_REQUEST);

    }

    private String extractFormatFromName(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
