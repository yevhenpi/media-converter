package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;


public interface RequestProcessor<T extends RequestDTO> {
    ResponseEntity<?> processRequest(@Valid T requestDTO);
}
