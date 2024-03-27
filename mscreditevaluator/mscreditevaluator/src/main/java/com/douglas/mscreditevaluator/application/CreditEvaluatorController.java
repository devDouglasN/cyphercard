package com.douglas.mscreditevaluator.application;

import com.douglas.mscreditevaluator.application.exception.CardRequestErrorException;
import com.douglas.mscreditevaluator.application.exception.CustomerDataNotFoundException;
import com.douglas.mscreditevaluator.application.exception.ErrorComminucationMicroservicesException;
import com.douglas.mscreditevaluator.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity performReviews(@RequestBody DataEvaluation dataEvaluation){
        try {
            ReturnCustomerReview returnCustomerReview = creditEvaluatorService
                    .performReviews(dataEvaluation.getCpf(), dataEvaluation.getIncome());
            return ResponseEntity.ok(returnCustomerReview);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComminucationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("card-requests")
    public ResponseEntity requestCard(@RequestBody CardIssuanceRequestData data){
        try{
            CardRequestProtocol cardRequestProtocol = creditEvaluatorService.requestIssuanceCard(data);
            return ResponseEntity.ok(cardRequestProtocol);
        }catch (CardRequestErrorException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
