package ua.pidopryhora.mediaconverter.filemanager.service.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioRequestDTO;

@Slf4j
@Service
public class UpdateProducer {

    private final RabbitTemplate rabbitTemplate;

    public UpdateProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(String queueName, AudioRequestDTO requestDTO){
        rabbitTemplate.convertAndSend(queueName, requestDTO);

    }
}
