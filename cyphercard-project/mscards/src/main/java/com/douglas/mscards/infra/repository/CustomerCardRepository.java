package com.douglas.mscards.infra.repository;

import com.douglas.mscards.domain.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {

    List<CustomerCard> findByCpf(String cpf);
}
