package ua.pidopryhora.mediaconverter.filemanager.service.jave2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.jave2.JAVEDataSupplier;
import ua.pidopryhora.mediaconverter.common.model.RequestDTO;

@Service
@AllArgsConstructor
public class FormatValidationService {

    private final JAVEDataSupplier JAVEDataSupplier;

    public boolean isFormatValid(RequestDTO requestDTO) {
        var fileFormat = extractFormatFromName(requestDTO.getFileName());

        return (isFileFormatValid(fileFormat));
    }

    private String extractFormatFromName(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private boolean isFileFormatValid(String fileFormat)  {
        String[] formats = JAVEDataSupplier.getAudioFormats();
        boolean isPresent = false;
        for (String s:formats){
            if (s.equals(fileFormat)) {
                isPresent = true;
                break;
            }
        }

        return isPresent;
    }

}
