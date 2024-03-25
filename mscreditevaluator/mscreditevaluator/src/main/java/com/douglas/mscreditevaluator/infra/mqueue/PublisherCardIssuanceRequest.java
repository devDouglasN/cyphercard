package com.douglas.mscreditevaluator.infra.mqueue;


import com.douglas.mscreditevaluator.domain.model.CardIssuanceRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherCardIssuanceRequest {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardIssuance;

    public void requestCard(CardIssuanceRequestData data) throws JsonProcessingException{
        var json = converteIntoJson(data);
        rabbitTemplate.convertAndSend(queueCardIssuance.getName(), json);
    }

    private String converteIntoJson(CardIssuanceRequestData dados)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dados);
        return json;
    }
}
