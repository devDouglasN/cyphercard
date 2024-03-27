package com.douglas.mscreditevaluator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.card-issuance}")
    private String issuanceQueueCards;

    @Bean
    public Queue queueCardIssuance(){
        return new Queue(issuanceQueueCards , true);
    }
}
