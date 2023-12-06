package com.mfdesenvolvimento.marketplace.Customer;

import java.util.List;
import java.util.UUID;

import com.mfdesenvolvimento.marketplace.products.ProductsModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class CustomerModel {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(length = 50)
	private UUID id;
	private String name;
	private String telefone;

	@ManyToMany
	@JoinTable(name = "customer_products", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))

	private List<ProductsModel> selectedProducts;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<ProductsModel> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<ProductsModel> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

}
