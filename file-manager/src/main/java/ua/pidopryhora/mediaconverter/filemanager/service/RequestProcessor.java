package ua.pidopryhora.mediaconverter.filemanager.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestDTO;


public interface RequestProcessor<T extends RequestDTO> {
    ResponseEntity<?> processRequest(@Valid T requestDTO);
}
