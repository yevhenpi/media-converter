package ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.common.model.JobDTO;

@Slf4j
@Service
public class RabbitUpdateProducer implements UpdateProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitUpdateProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(String queueName, JobDTO audioJobDTO){
        rabbitTemplate.convertAndSend(queueName, audioJobDTO);


    }
}
