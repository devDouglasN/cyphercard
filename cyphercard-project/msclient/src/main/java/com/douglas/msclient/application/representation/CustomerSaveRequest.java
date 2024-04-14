package com.douglas.msclient.application.representation;

import com.douglas.msclient.domain.Customer;


public record CustomerSaveRequest (String cpf, String name, Integer age){

    public Customer toModel(){
        return new Customer(
                cpf,
                name,
                age);
    }
}
