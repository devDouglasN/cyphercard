package com.douglas.mscards.application.representation;

import com.douglas.mscards.domain.CustomerCard;
import com.douglas.mscards.infra.repository.CustomerCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCardService {

    @Autowired
    private CustomerCardRepository repository;

    public List<CustomerCard> listCardsByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
