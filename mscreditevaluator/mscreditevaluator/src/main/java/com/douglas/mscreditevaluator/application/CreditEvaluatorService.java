package com.douglas.mscreditevaluator.application;

import com.douglas.mscreditevaluator.domain.model.CustomerCard;
import com.douglas.mscreditevaluator.domain.model.CustomerData;
import com.douglas.mscreditevaluator.domain.model.CustomerSituation;
import com.douglas.mscreditevaluator.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final CustomerResourceClient customersClient;

    public CustomerSituation getCustomerSituation(String cpf){

        ResponseEntity<CustomerData> dataCustomerResponse = customersClient.dataCustomer(cpf);

        return CustomerSituation
                .builder()
                .client(dataCustomerResponse.getBody())
                .build();
    }
}
