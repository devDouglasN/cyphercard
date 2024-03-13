package com.douglas.msclient.application;

import com.douglas.msclient.domain.Customer;
import com.douglas.msclient.infra.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
