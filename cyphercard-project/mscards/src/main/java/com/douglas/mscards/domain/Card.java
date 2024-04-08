package com.douglas.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private FlagCard flag;
    private BigDecimal income;
    private BigDecimal limitBasic;

    public Card(String name, FlagCard flag, BigDecimal income, BigDecimal limitBasic) {
        this.name = name;
        this.flag = flag;
        this.income = income;
        this.limitBasic = limitBasic;
    }
}
