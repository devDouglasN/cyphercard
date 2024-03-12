package com.douglas.msclient.infra.repository;

import com.douglas.msclient.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
