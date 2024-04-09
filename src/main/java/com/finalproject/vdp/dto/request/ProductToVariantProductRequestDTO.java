package com.finalproject.vdp.dto.request;

import com.finalproject.vdp.model.Product;

import lombok.Data;

@Data
public class ProductToVariantProductRequestDTO {
	private Integer productID;
	private String productName;

	public Product toProduct() {
		Product product = new Product();
		product.setProductID(this.productID);
		product.setProductName(this.productName);
		return product;
	}
}
