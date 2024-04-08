package com.douglas.mscards.application.representation;

import com.douglas.mscards.domain.Card;
import com.douglas.mscards.domain.FlagCard;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CardSaveRequest {

    private String name;
    private FlagCard flagCard;
    private BigDecimal income;
    private BigDecimal limitBasic;

    public Card toModel(){
        return new Card(name, flagCard, income, limitBasic);
    }
}
