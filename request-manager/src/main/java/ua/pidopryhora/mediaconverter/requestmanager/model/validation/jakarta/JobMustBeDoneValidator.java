package ua.pidopryhora.mediaconverter.requestmanager.model.validation.jakarta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;

import static ua.pidopryhora.mediaconverter.common.model.JobStatus.DONE;

public class JobMustBeDoneValidator implements ConstraintValidator<JobMustBeDone, String> {

    private final JobDataService jobDataService;

    public JobMustBeDoneValidator(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @Override
    public boolean isValid(String jobId, ConstraintValidatorContext constraintValidatorContext) {
        return jobDataService.getJob(jobId).getJobStatus().equals(String.valueOf(DONE));
    }
}
