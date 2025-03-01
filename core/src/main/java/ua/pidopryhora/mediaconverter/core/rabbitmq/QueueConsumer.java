package ua.pidopryhora.mediaconverter.core.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;

@Slf4j
@Service
public class QueueConsumer {
    @RabbitListener(queues = AUDIO_CONVERSION_QUEUE)
    public void consumeConversionQueue(AudioJobDTO audioJobDTO){



    }
}
