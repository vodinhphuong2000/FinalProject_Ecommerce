package com.finalproject.vdp.dto.response;

import com.finalproject.vdp.model.VariantProduct;

import lombok.Data;

@Data
public class VariantProductResponseDTO {
	private Integer variant_productID;
	private String color;
	private String size;
	private String model;
	private Double price;
	
	public VariantProductResponseDTO(VariantProduct variantProduct) {
		this.variant_productID=variantProduct.getVariant_productID();
		this.color=variantProduct.getColor();
		this.size=variantProduct.getSize();
		this.price=variantProduct.getPrice();
		this.model=variantProduct.getModel();
		
	}
}
