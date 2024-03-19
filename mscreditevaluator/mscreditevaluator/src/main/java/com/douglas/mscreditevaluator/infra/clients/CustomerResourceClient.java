package com.douglas.mscreditevaluator.infra.clients;

import com.douglas.mscreditevaluator.domain.model.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<CustomerData> dataCustomer(@RequestParam("cpf") String cpf);
}
