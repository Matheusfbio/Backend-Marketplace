package com.mfdesenvolvimento.marketplace.employee;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepository extends CrudRepository<EmployeeModel, UUID> {
	EmployeeModel findByUsername(String username);
}
