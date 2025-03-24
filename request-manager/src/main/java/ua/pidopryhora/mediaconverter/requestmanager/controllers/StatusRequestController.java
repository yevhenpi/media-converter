package ua.pidopryhora.mediaconverter.requestmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/jobs")
public class StatusRequestController {
    private final JobDataService jobDataService;

    public StatusRequestController(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @PostMapping("/status")
    public ResponseEntity<?> getStatus(@RequestHeader("UserId") String userId,
                                       @RequestBody List<String> jobIdList){


        if(jobIdList.isEmpty()) return ResponseEntity.ok(jobDataService.getAllStatuses(Long.valueOf(userId)));

        return ResponseEntity.ok(jobDataService.getStatuses(jobIdList));
    }
}
