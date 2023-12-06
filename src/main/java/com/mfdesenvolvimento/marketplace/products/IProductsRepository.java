package com.mfdesenvolvimento.marketplace.products;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductsRepository extends JpaRepository<ProductsModel, UUID> {
	List<ProductsModel> findByIdEmployee(UUID idEmployee);

	ProductsModel findByIdAndIdEmployee(UUID id, UUID idEmployee);
}
