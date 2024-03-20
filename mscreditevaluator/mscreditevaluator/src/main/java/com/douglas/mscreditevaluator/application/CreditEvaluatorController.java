package com.douglas.mscreditevaluator.application;

import com.douglas.mscreditevaluator.domain.model.CustomerSituation;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessments-credit")
@RequiredArgsConstructor
public class CreditEvaluatorController {

    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping(value = "situation-customer", params = "cpf")
    public ResponseEntity<CustomerSituation> customerSituationConsultation(@RequestParam("cpf") String cpf){
        CustomerSituation situationCustomer = creditEvaluatorService.getCustomerSituation(cpf);
        return ResponseEntity.ok(situationCustomer);
    }
}
