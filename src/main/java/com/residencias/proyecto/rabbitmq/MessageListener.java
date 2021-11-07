package com.residencias.proyecto.rabbitmq;

import com.residencias.proyecto.dto.ReunionStatus;
import com.residencias.proyecto.rabbitmq.config.MessagingConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void listener(ReunionStatus message){
        System.out.println(message);
    }

}
