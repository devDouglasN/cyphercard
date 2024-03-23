package com.douglas.mscreditevaluator.application;

import com.douglas.mscreditevaluator.application.exception.CustomerDataNotFoundException;
import com.douglas.mscreditevaluator.application.exception.ErrorComminucationMicroservicesException;
import com.douglas.mscreditevaluator.domain.model.*;
import com.douglas.mscreditevaluator.infra.clients.CardsResourceClients;
import com.douglas.mscreditevaluator.infra.clients.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final CustomerResourceClient customersClient;

    private final CardsResourceClients cardsClients;

    public CustomerSituation getCustomerSituation(String cpf) throws CustomerDataNotFoundException, ErrorComminucationMicroservicesException {

        try {
            ResponseEntity<CustomerData> dataCustomerResponse = customersClient.dataCustomer(cpf);
            ResponseEntity<List<CustomerCard>> cardsResponse = cardsClients.getCardsByCustomer(cpf);

            return CustomerSituation
                    .builder()
                    .client(dataCustomerResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new ErrorComminucationMicroservicesException(e.getMessage(), status);
        }
    }

    public ReturnCustomerReview performReviews(String cpf, Long income)
            throws CustomerDataNotFoundException, ErrorComminucationMicroservicesException{
        try {
            ResponseEntity<CustomerData> dataCustomerResponse = customersClient.dataCustomer(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsClients.listCardsbyIncome(income);

            List<Card> cards = cardsResponse.getBody();

            var listCardsApproved = cards.stream().map(card -> {
                CustomerData dataCustomer = dataCustomerResponse.getBody();

                BigDecimal basicLimit = card.getLimitBasic();
                BigDecimal ageDB = BigDecimal.valueOf(dataCustomer.getAge());
                var factor = ageDB.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = factor.multiply(basicLimit);

                CardApproved approved = new CardApproved();
                approved.setCard(card.getName());
                approved.setFlag(card.getFlag());
                approved.setLimitApproved(limitApproved);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnCustomerReview(listCardsApproved);

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new ErrorComminucationMicroservicesException(e.getMessage(), status);
        }
    }
}
