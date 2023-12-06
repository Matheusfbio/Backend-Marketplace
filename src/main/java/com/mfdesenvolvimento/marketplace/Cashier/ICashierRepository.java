package com.mfdesenvolvimento.marketplace.Cashier;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICashierRepository extends JpaRepository<CashierModel, UUID> {

}
