package com.douglas.mscreditevaluator.domain.model;


import java.math.BigDecimal;


public record CustomerCard(

        String name,
        String flag,
        BigDecimal limitReleased) {
}
