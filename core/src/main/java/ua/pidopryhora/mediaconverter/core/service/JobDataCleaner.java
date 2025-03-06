package ua.pidopryhora.mediaconverter.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.data.JobData;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.core.s3.S3Deleter;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class JobDataCleaner {

    private final JobDataService jobDataService;
    private final S3Deleter s3Deleter;

    public JobDataCleaner(JobDataService jobDataService, S3Deleter s3Deleter) {
        this.jobDataService = jobDataService;
        this.s3Deleter = s3Deleter;
        cleanExpiredJobs();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleCleanup() {
        cleanExpiredJobs();
    }
    private void cleanExpiredJobs(){
        log.debug("Cleaning jobs...");
        LocalDateTime expiry = LocalDateTime.now().minusDays(1);
        List<JobData> jobsToDelete = jobDataService.deleteExpired(expiry);
        s3Deleter.batchDelete(jobsToDelete);
    }

}
