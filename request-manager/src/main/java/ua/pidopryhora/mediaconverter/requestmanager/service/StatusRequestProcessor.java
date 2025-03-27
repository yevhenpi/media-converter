package ua.pidopryhora.mediaconverter.requestmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.model.StatusRequestDTO;
@Service
@RequiredArgsConstructor
public class StatusRequestProcessor implements RequestProcessor<StatusRequestDTO> {

    private final JobDataService jobDataService;

    private final StatusRequestValidationService requestValidationService;
    @Override
    public ResponseEntity<?> processRequest(StatusRequestDTO requestDTO) {

        if(!(requestDTO.getJobIds() == null)) requestValidationService.validateRequest(requestDTO);

        if((requestDTO.getJobIds() == null)) return ResponseEntity.ok(jobDataService.getAllStatuses(requestDTO.getUserId()));

        return ResponseEntity.ok(jobDataService.getStatuses(requestDTO.getJobIds()));
    }
}
