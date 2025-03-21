package ua.pidopryhora.mediaconverter.core.service;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.common.data.JobData;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.core.config.DirectoryConstants;
import ua.pidopryhora.mediaconverter.core.s3.S3Deleter;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class DataCleaner {

    private final DirectoryCleaner directoryCleaner;
    private final JobDataService jobDataService;
    private final S3Deleter s3Deleter;

    public DataCleaner(DirectoryCleaner directoryCleaner,
                       JobDataService jobDataService,
                       S3Deleter s3Deleter) {
        this.directoryCleaner = directoryCleaner;
        this.jobDataService = jobDataService;
        this.s3Deleter = s3Deleter;
        cleanExpiredJobs();
        directoryCleanup();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleCleanup() {
        cleanExpiredJobs();
        directoryCleanup();
    }

    @PreDestroy
    public void shutdownCleanup(){
        directoryCleanup();
    }

    private void cleanExpiredJobs(){
        log.debug("Cleaning jobs...");
        LocalDateTime expiry = LocalDateTime.now().minusDays(1);
        List<JobData> jobsToDelete = jobDataService.deleteExpired(expiry);
        if(!jobsToDelete.isEmpty()) s3Deleter.batchDelete(jobsToDelete);
    }

    private void directoryCleanup(){
        directoryCleaner.cleanDirectory(DirectoryConstants.INPUT_DIRECTORY);
        directoryCleaner.cleanDirectory(DirectoryConstants.OUTPUT_DIRECTORY);
    }

}
