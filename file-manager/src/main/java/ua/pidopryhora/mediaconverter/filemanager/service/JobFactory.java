package ua.pidopryhora.mediaconverter.filemanager.service;

import ua.pidopryhora.mediaconverter.common.model.JobDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestDTO;

public interface JobFactory <T, R>{
    R createJob(T requestDTO, String jobId);
}
