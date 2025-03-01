package ua.pidopryhora.mediaconverter.core.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import ua.pidopryhora.mediaconverter.common.model.JobDTO;

@Slf4j
@Service
public class QueueConsumer {
    @RabbitListener(queues = "CONVERSION_QUEUE")
    public void consumeConversionQueue(JobDTO jobDTO){



    }
}
