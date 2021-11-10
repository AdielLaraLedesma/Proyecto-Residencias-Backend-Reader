package com.residencias.proyecto.rabbitmq;

import com.residencias.proyecto.dto.ReunionStatus;
import com.residencias.proyecto.rabbitmq.config.MessagingConfig;
import com.residencias.proyecto.repository.ReunionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private ReunionRepository repository;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void listener(ReunionStatus message){

        System.out.println(message);
        repository.save(message.getReunion());
    }

}
