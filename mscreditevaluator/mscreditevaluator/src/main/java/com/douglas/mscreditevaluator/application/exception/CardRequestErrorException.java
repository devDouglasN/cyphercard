package com.douglas.mscreditevaluator.application.exception;

public class CardRequestErrorException extends RuntimeException{

    public CardRequestErrorException(String message) {
        super(message);
    }
}
