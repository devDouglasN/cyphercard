package com.douglas.mscreditevaluator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSituation {

    private CustomerData client;
    private List<CustomerCard> cards;
}
