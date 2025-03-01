package ua.pidopryhora.mediaconverter.core.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.core.service.JobProcessor;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.AUDIO_CONVERSION_QUEUE;


@Slf4j
@Service
@AllArgsConstructor
public class QueueConsumer {

    private final JobProcessor<AudioJobDTO> jobProcessor;

    @RabbitListener(queues = AUDIO_CONVERSION_QUEUE)
    public void consumeConversionQueue(AudioJobDTO audioJobDTO){
        jobProcessor.processJob(audioJobDTO);
    }
}
