package ua.pidopryhora.mediaconverter.filemanager.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.common.model.RequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

public interface RequestProcessor<T extends RequestDTO> {
    ResponseEntity<?> processRequest(T requestDTO);
}
