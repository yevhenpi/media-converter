package ua.pidopryhora.mediaconverter.requestmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FileAlreadyUploadedException extends RuntimeException {
    public FileAlreadyUploadedException(String message) {
        super(message);
    }
}
