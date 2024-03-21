package com.douglas.mscreditevaluator.application;

import com.douglas.mscreditevaluator.application.exception.CustomerDataNotFoundException;
import com.douglas.mscreditevaluator.application.exception.ErrorComminucationMicroservicesException;
import com.douglas.mscreditevaluator.domain.model.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity customerSituationConsultation(@RequestParam("cpf") String cpf){
        CustomerSituation situationCustomer = null;
        try {
            situationCustomer = creditEvaluatorService.getCustomerSituation(cpf);
            return ResponseEntity.ok(situationCustomer);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComminucationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
