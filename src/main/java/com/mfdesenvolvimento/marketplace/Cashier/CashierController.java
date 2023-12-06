package com.mfdesenvolvimento.marketplace.Cashier;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfdesenvolvimento.marketplace.products.IProductsRepository;
import com.mfdesenvolvimento.marketplace.products.ProductsModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cashier")
public class CashierController {

	@Autowired
	private ICashierRepository cashierRepository; // Certifique-se de injetar o repositório correspondente

	@Autowired
	private IProductsRepository productsRepository; // Certifique-se de injetar o repositório correspondente

	// Endpoint para criar um novo caixa
	@PostMapping("/")
	public ResponseEntity<CashierModel> createCashier(@RequestBody CashierModel cashierModel) {
		CashierModel savedCashier = cashierRepository.save(cashierModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCashier);
	}

	// Endpoint para obter todos os caixas
	@GetMapping("/")
	public List<CashierModel> getAllCashiersList() {
		return cashierRepository.findAll();
	}

	// Endpoint para obter um caixa pelo ID
	@GetMapping("/id")
	public ResponseEntity<CashierModel> getCashierById(@PathVariable UUID id) {
		Optional<CashierModel> cashierOptional = cashierRepository.findById(id);
		return cashierOptional.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Endpoint para atualizar um caixa existente
	@PutMapping("/id")
	public ResponseEntity<CashierModel> updateCashier(@PathVariable UUID id,
			@RequestBody CashierModel updatedCashier) {
		if (!cashierRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		updatedCashier.setId(id);
		CashierModel savedCashier = cashierRepository.save(updatedCashier);
		return ResponseEntity.ok(savedCashier);
	}

	// Endpoint para excluir um caixa pelo ID
	@DeleteMapping("/id")
	public ResponseEntity<Void> deleteCashier(@PathVariable UUID id) {
		if (!cashierRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		cashierRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	// Endpoint para adicionar produtos a um caixa
	@PostMapping("/{id}")
	public ResponseEntity<CashierModel> addProductsToCashier(@PathVariable UUID id,
			@RequestBody List<UUID> productIds) {
		Optional<CashierModel> cashierOptional = cashierRepository.findById(id);
		if (cashierOptional.isPresent()) {
			CashierModel cashier = cashierOptional.get();
			List<ProductsModel> products = productsRepository.findAllById(productIds);
			cashier.getSelectedProducts().addAll(products);

			CashierModel updatedCashier = cashierRepository.save(cashier);
			return ResponseEntity.ok(updatedCashier);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint para remover produtos de um caixa
	@DeleteMapping("/{id}")
	public ResponseEntity<CashierModel> removeProductsFromCashier(@PathVariable UUID id,
			@RequestBody List<UUID> productIds) {
		Optional<CashierModel> cashierOptional = cashierRepository.findById(id);
		if (cashierOptional.isPresent()) {
			CashierModel cashier = cashierOptional.get();
			List<ProductsModel> products = productsRepository.findAllById(productIds);
			cashier.getSelectedProducts().removeAll(products);

			CashierModel updatedCashier = cashierRepository.save(cashier);
			return ResponseEntity.ok(updatedCashier);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
