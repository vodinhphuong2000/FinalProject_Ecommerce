package com.finalproject.vdp.dto.request;

import com.finalproject.vdp.model.VariantProduct;

import lombok.Data;

@Data
public class VariantProductRequestDTO {
	private Integer productID;
	private String color;
	private String size;
	private String model;
	private Double price;

	public VariantProduct toVariantProduct() {
		VariantProduct variantProduct = new VariantProduct();
		variantProduct.setColor(this.color);
		variantProduct.setSize(this.size);
		variantProduct.setModel(this.model);
		variantProduct.setPrice(this.price);
		return variantProduct;
	}
}
