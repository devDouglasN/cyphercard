package com.douglas.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardIssuanceRequestData {

    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
