package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.requestmanager.model.StatusRequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.StatusRequestProcessor;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/jobs")
public class StatusRequestController {

    private final StatusRequestProcessor requestProcessor;

    public StatusRequestController(StatusRequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }
    //TODO: Job existence validation is needed

    @PostMapping("/status")
    public ResponseEntity<?> getStatus(@RequestHeader("UserId") String userId,
                                       @RequestHeader("UserRole") String userRole,
                                       @RequestBody StatusRequestDTO requestDTO){
        log.debug(requestDTO.getJobIds().toString());


        requestDTO.setRole(userRole);
        requestDTO.setUserId(Long.parseLong(userId));

        return requestProcessor.processRequest(requestDTO);
    }
}
