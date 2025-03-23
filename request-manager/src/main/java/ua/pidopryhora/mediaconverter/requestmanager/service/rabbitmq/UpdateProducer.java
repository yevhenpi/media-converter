package ua.pidopryhora.mediaconverter.requestmanager.service.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;

@Slf4j
@Service
public class UpdateProducer {

    private final RabbitTemplate rabbitTemplate;

    public UpdateProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(String queueName, AudioJobDTO audioJobDTO){
        rabbitTemplate.convertAndSend(queueName, audioJobDTO);


    }
}
