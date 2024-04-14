package com.douglas.mscards.application.representation;

import com.douglas.mscards.domain.Card;
import com.douglas.mscards.domain.FlagCard;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

public record CardSaveRequest(String name, FlagCard flagCard, BigDecimal income, BigDecimal limitBasic) {
    public Card toModel() {
        return new Card(name, flagCard, income, limitBasic);
    }
}