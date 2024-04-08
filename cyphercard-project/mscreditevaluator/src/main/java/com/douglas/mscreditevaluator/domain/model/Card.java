package com.douglas.mscreditevaluator.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Card {

    private Long id;
    private String name;
    private String flag;
    private BigDecimal limitBasic;
}
