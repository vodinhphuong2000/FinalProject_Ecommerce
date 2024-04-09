package com.finalproject.vdp.dto.response;

import com.finalproject.vdp.model.Product;

import lombok.Data;
@Data
public class AddProductResponseDTO {
	private Integer productID;
	private String productName;

	public AddProductResponseDTO(Product product) {
		this.productID = product.getProductID();
		this.productName = product.getProductName();
	}
}
