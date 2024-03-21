package com.douglas.mscreditevaluator.infra.clients;

import com.douglas.mscreditevaluator.domain.model.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClients {

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("cpf") String cpf);
}
