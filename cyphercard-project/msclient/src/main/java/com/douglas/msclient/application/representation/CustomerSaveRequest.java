package com.douglas.msclient.application.representation;

import com.douglas.msclient.domain.Customer;
import lombok.Data;

@Data
public class CustomerSaveRequest {

    private String cpf;
    private String name;
    private Integer age;

    public Customer toModel(){
        return new Customer(cpf, name, age);
    }
}
