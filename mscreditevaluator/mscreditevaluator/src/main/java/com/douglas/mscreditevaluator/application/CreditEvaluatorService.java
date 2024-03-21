package com.douglas.mscreditevaluator.application;

import com.douglas.mscreditevaluator.application.exception.CustomerDataNotFoundException;
import com.douglas.mscreditevaluator.application.exception.ErrorComminucationMicroservicesException;
import com.douglas.mscreditevaluator.domain.model.CustomerCard;
import com.douglas.mscreditevaluator.domain.model.CustomerData;
import com.douglas.mscreditevaluator.domain.model.CustomerSituation;
import com.douglas.mscreditevaluator.infra.clients.CardsResourceClients;
import com.douglas.mscreditevaluator.infra.clients.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
