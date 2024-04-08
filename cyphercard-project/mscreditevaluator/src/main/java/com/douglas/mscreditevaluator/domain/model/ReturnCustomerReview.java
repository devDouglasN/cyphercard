package com.douglas.mscreditevaluator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnCustomerReview {

    private List<CardApproved> cards;
}
