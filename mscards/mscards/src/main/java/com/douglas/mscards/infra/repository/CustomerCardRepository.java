package com.douglas.mscards.infra.repository;

import com.douglas.mscards.domain.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {
}
