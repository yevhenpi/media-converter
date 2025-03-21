package ua.pidopryhora.mediaconverter.common.data;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.JobDTO;
import ua.pidopryhora.mediaconverter.common.model.JobStatusDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ua.pidopryhora.mediaconverter.common.model.JobStatus.PROCESSING;
@Service
public class JobDataService {

    private final JobDataRepository jobDataRepository;

    public JobDataService(JobDataRepository jobDataRepository) {
        this.jobDataRepository = jobDataRepository;
    }

    public JobData saveData(JobDTO jobDTO){
        return jobDataRepository.save(
                JobData.builder()
                        .jobId(jobDTO.getJobId())
                        .ownerId(jobDTO.getUserId())
                        .fileName(jobDTO.getS3Key())
                        .jobStatus(String.valueOf(PROCESSING))
                        .build()
        );
    }

    public void updateJobStatus(String jobId, String newStatus) {
        int updatedRows = jobDataRepository.updateJobStatus(jobId, newStatus);
        if (updatedRows == 0) {
            throw new EntityNotFoundException("Job data not found for jobId: " + jobId);
        }
    }

    public boolean isPresent(String jobId){
        return jobDataRepository.existsByJobId(jobId);
    }

    public List<JobData> getJobs(List<String> jobId){
        List<JobData> jobs = new ArrayList<>();
        for(String job:jobId){
            jobs.add(jobDataRepository.getByJobId(job));
        }
        return jobs;
    }

    public JobData getJob(String jobId){
        return jobDataRepository.getByJobId(jobId);
    }

    public List<JobData> getUserJobs(Long userId){
        return jobDataRepository.findByOwnerId(userId);
    }

    public List<JobStatusDto> getAllStatuses(Long userId){
        return jobDataRepository.findByOwnerId(userId)
                .stream()
                .map(jobData -> new JobStatusDto(jobData.getJobId(), jobData.getJobStatus()))
                .toList();

    }
    public List<JobStatusDto> getStatuses(List<String> jobId){
        return   getJobs(jobId)
                .stream()
                .map(jobData -> new JobStatusDto(jobData.getJobId(), jobData.getJobStatus()))
                .toList();

    }

    public String getS3Key(String jobId){
        return jobDataRepository.findS3KeyByJobId(jobId);
    }

    public void updateS3Key(String jobId,String key){
        jobDataRepository.updateS3Key(jobId,key);
    }

    public List<JobData> deleteExpired(LocalDateTime threshold){
        List<JobData> jobsToDelete = jobDataRepository.findByCreatedAtBefore(threshold);

        jobDataRepository.deleteAll(jobsToDelete);

        return jobsToDelete;
    }
}
