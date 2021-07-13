package com.votacao.cooperativa;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableRabbit
@SpringBootApplication
public class CooperativaApplication {

    @Value("${queue.order.voteFinalize}")
    private String orderQueue;

    public static void main(String[] args) {
        SpringApplication.run(CooperativaApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new Queue(orderQueue, true);
    }

}
