package ua.pidopryhora.mediaconverter.filemanager.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.pidopryhora.mediaconverter.common.data.JobDataService;
import ua.pidopryhora.mediaconverter.filemanager.model.DownloadRequestDTO;

public class JobMustExistValidator implements ConstraintValidator<JobMustExist, String> {

    private final JobDataService jobDataService;

    public JobMustExistValidator(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @Override
    public boolean isValid(String jobId, ConstraintValidatorContext constraintValidatorContext) {
        return jobDataService.isPresent(jobId);
    }
}
