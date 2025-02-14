package ua.pidopryhora.mediaconverter.filemanager.service.jave2;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.jave2.FormatSupplier;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

@Service
@AllArgsConstructor
public class FormatValidationService {

    private final FormatSupplier formatSupplier;

    public boolean isFormatValid(UploadRequestDTO metadata) {
        var fileFormat = extractFormatFromName(metadata.getFileName());

        return (isFileFormatValid(fileFormat));
    }

    private String extractFormatFromName(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private boolean isFileFormatValid(String fileFormat)  {
        String[] formats = formatSupplier.getDecodingFormats();
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
