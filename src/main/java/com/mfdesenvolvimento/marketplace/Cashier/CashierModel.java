package com.mfdesenvolvimento.marketplace.Cashier;

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

@Entity(name = "tb_cashier")
public class CashierModel {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(length = 50)
	private UUID id;
	private UUID idEmployee;

	@ManyToMany
	@JoinTable(name = "cashier_products", joinColumns = @JoinColumn(name = "cashier_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<ProductsModel> selectedProducts;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(UUID idEmployee) {
		this.idEmployee = idEmployee;
	}

	public List<ProductsModel> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<ProductsModel> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public Object copyFromCashierModel() {
		return null;
	}
}
