package com.douglas.mscards.application.representation;

import com.douglas.mscards.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPerCustomerResponse {

    private String name;
    private String flag;
    private BigDecimal limitReleased;

    public static CardsPerCustomerResponse fromModel(CustomerCard card){
        return new CardsPerCustomerResponse(card.getCard().getName(),
                card.getCard().getFlag().toString(),
                card.getLimitCard());
    }
}
