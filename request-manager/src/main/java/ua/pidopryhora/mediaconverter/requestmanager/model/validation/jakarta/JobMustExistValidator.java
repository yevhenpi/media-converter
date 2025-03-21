package ua.pidopryhora.mediaconverter.requestmanager.model.validation.jakarta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;

public class JobMustExistValidator implements ConstraintValidator<JobMustExist, String> {
    //TODO: Should return 404 if job not exists

    private final JobDataService jobDataService;

    public JobMustExistValidator(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @Override
    public boolean isValid(String jobId, ConstraintValidatorContext constraintValidatorContext) {
        return jobDataService.isPresent(jobId);
    }
}
