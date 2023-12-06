package com.mfdesenvolvimento.marketplace.Customer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerModel, UUID> {

}
