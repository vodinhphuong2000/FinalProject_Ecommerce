package com.finalproject.vdp.dto.request;

import com.finalproject.vdp.model.VariantProduct;

import lombok.Data;

@Data
public class AddVariantProductToCartLineItemrRequestDTO {
	private Integer variant_productID;
	private Integer oderID;
	private String color;
	private String size;
	private String model;
	private Double price;

	public AddVariantProductToCartLineItemrRequestDTO() {
		VariantProduct variantProduct = new VariantProduct();
		variantProduct.setVariant_productID(variant_productID);
		variantProduct.setColor(color);
		variantProduct.setSize(size);
		variantProduct.setModel(model);
		variantProduct.setPrice(price);

	}
}
