package ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq;

import ua.pidopryhora.mediaconverter.common.model.JobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.JobRequestDTO;

public interface UpdateProducer {

    void produce(String queueName, JobDTO jobDTO);
}
