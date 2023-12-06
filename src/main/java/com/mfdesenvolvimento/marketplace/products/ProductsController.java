package com.mfdesenvolvimento.marketplace.products;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfdesenvolvimento.marketplace.utlis.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private IProductsRepository productsRepository;

	@GetMapping("/")
	public List<ProductsModel> list(HttpServletRequest request) {
		var idEmployee = request.getAttribute("idEmployee");
		var product = this.productsRepository.findByIdEmployee((UUID) idEmployee);
		return product;
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody ProductsModel productsModel, HttpServletRequest request) {
		var idEmployee = request.getAttribute("idEmployee");
		productsModel.setIdEmployee((UUID) idEmployee);

		var product = this.productsRepository.save(productsModel);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody ProductsModel productsModel, @PathVariable UUID id,
			HttpServletRequest request) {

		var products = this.productsRepository.findById(id).orElse(null);

		if (products == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Product not found");
		}

		var idEmployee = request.getAttribute("idEmployee");

		if (!products.getIdEmployee().equals(idEmployee)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Employee does not permition to change the products");
		}

		Utils.copyNonNullProperties(productsModel, products);
		var productUpdated = this.productsRepository.save(products);
		return ResponseEntity.ok().body(productUpdated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@RequestBody ProductsModel productsModel, @PathVariable UUID id,
			HttpServletRequest request) {

		var product = this.productsRepository.findById(id).orElse(null);

		if (product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Product not found");
		}

		productsRepository.delete(product);

		return ResponseEntity.ok().body("Product deleted successfully");
	}
}
