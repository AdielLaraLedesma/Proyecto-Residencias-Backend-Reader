package com.residencias.proyecto.rabbitmq;

import com.residencias.proyecto.dto.Participante;
import com.residencias.proyecto.dto.ReunionStatus;
import com.residencias.proyecto.rabbitmq.config.MessagingConfig;
import com.residencias.proyecto.repository.ReunionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageListener {

    @Autowired
    private ReunionRepository repository;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void listener(ReunionStatus message){

        System.out.println(message);

        List<Participante> participantes = new ArrayList();

        message.getReunion().getParticipantes().forEach( (element) -> {
            if(element.isAsistencia())
                participantes.add(element);
        });

        message.getReunion().setParticipantes(participantes);


        repository.save(message.getReunion());
    }

}
