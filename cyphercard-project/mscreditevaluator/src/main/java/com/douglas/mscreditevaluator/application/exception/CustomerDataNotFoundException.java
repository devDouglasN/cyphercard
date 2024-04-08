package com.douglas.mscreditevaluator.application.exception;

public class CustomerDataNotFoundException extends Exception{

    public CustomerDataNotFoundException() {
        super("No records found for the provided CPF");
    }
}
