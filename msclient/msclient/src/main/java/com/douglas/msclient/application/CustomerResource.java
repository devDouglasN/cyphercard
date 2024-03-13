package com.douglas.msclient.application;

import com.douglas.msclient.application.representation.CustomerSaveRequest;
import com.douglas.msclient.domain.Customer;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customers")
public class CustomerResource {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<Customer> save(CustomerSaveRequest request){
        Customer customer = request.toModel();
        service.saveCustomer(customer);
        URI positionHeader = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(customer.getCpf())
                .toUri();
        return ResponseEntity.created(positionHeader).build();
    }
}
