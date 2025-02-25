package ua.pidopryhora.mediaconverter.common.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ua.pidopryhora.mediaconverter.common.rabbitmq.RabbitQueues.CONVERSION_QUEUE;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue(CONVERSION_QUEUE, false);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
