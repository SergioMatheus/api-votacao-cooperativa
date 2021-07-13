package com.votacao.cooperativa.producer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class VotacaoFinalizeConsumer {

    @RabbitListener(queues = {"${queue.order.voteFinalize}"})
    public void receive(@Payload String voteObject) {
        System.out.println("Objeto consumido da fila: " + voteObject);
    }
}