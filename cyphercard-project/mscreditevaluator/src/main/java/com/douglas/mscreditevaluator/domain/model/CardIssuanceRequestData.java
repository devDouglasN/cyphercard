package com.douglas.mscreditevaluator.domain.model;


import java.math.BigDecimal;


public record CardIssuanceRequestData (

        Long idCard,
        String cpf,
        String address,
        BigDecimal limitReleased){
}
