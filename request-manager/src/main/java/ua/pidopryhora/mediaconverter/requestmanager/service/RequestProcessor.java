package ua.pidopryhora.mediaconverter.requestmanager.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;


public interface RequestProcessor<T extends AuthenticatedRequestDTO> {
    ResponseEntity<?> processRequest(@Valid T requestDTO);
}
