package com.finalproject.vdp.dto.request;

import com.finalproject.vdp.model.Product;

import lombok.Data;
@Data
public class ProductRequestDTO {
	private Integer categoryID;
	private String productName;
	
	public Product toProduct() {
		Product product=new Product();
		product.setProductName(this.productName);
		return product;
	}
}
