package ua.pidopryhora.mediaconverter.requestmanager.service;


public interface JobFactory <T, R>{
    R createJob(T requestDTO);
}
