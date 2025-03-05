package ua.pidopryhora.mediaconverter.filemanager.service;


public interface JobFactory <T, R>{
    R createJob(T requestDTO, String jobId);
}
