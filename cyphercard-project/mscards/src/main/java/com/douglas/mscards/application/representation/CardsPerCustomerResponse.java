package com.douglas.mscards.application.representation;

import com.douglas.mscards.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record CardsPerCustomerResponse(

        String name,
        String flag,
        BigDecimal limitReleased) {
    public static CardsPerCustomerResponse fromModel(CustomerCard card) {
        return new CardsPerCustomerResponse(card.getCard().getName(),
                card.getCard().getFlag().toString(),
                card.getLimitCard());
    }
}
