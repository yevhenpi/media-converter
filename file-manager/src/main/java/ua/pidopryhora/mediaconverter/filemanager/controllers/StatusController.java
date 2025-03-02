package ua.pidopryhora.mediaconverter.filemanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/jobs")
public class StatusController {
    private final JobDataService jobDataService;

    public StatusController(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus(@RequestHeader("UserId") String userId,
                                       @RequestBody List<String> jobIdList){

        if(jobIdList.isEmpty()) return ResponseEntity.ok(jobDataService.getUserJobs(Long.valueOf(userId)));

        return ResponseEntity.ok(jobDataService.getJobs(jobIdList));
    }
}
