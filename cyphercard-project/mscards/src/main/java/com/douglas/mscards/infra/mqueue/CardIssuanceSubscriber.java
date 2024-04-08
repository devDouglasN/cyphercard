package com.douglas.mscards.infra.mqueue;

import com.douglas.mscards.domain.Card;
import com.douglas.mscards.domain.CardIssuanceRequestData;
import com.douglas.mscards.domain.CustomerCard;
import com.douglas.mscards.infra.repository.CardRepository;
import com.douglas.mscards.infra.repository.CustomerCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class CardIssuanceSubscriber {
    
    private final CardRepository cardRepository;
    private final CustomerCardRepository customerCardRepository;

    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void receiveRequestIssuance(@Payload String payload){
        System.out.println(payload);

        try {
            var mapper = new ObjectMapper();
            CardIssuanceRequestData data = mapper.readValue(payload, CardIssuanceRequestData.class);

            Card card = cardRepository.findById(data.getIdCard()).orElseThrow();
            CustomerCard customerCard = new CustomerCard();
            customerCard.setCard(card);
            customerCard.setCpf(data.getCpf());
            customerCard.setLimitCard(data.getLimitReleased());

            customerCardRepository.save(customerCard);

        }catch (Exception e){
            log.error("Error when receiving request for card issuance: {} ", e.getMessage());
        }
    }
}
