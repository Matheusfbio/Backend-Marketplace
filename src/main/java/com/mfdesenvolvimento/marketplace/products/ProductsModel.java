package com.mfdesenvolvimento.marketplace.products;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_products")
public class ProductsModel {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(length = 50)
	private UUID id;
	private String nameProduct;
	private String price;
	private UUID idEmployee;

	public void setnameProduct(String nameProduct) throws Exception {
		if (nameProduct.length() > 50) {
			throw new Exception("the field nameProduct must be at least 50 characters");
		}
		this.nameProduct = nameProduct;
	}
}
