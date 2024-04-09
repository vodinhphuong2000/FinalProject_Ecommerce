package com.finalproject.vdp.dto.response;

import com.finalproject.vdp.model.VariantProduct;

import lombok.Data;

@Data
public class ProductToVariantProductResponseDTO {
	private Integer productID;
	private String productName;
	private Integer variant_productID;
	private String color;
	private String size;
	private String model;
	private Double price;

	public ProductToVariantProductResponseDTO(VariantProduct variantProduct) {
		this.productID = variantProduct.getProducts().getProductID();
		this.productName = variantProduct.getProducts().getProductName();
		this.variant_productID = variantProduct.getVariant_productID();
		this.color = variantProduct.getColor();
		this.size = variantProduct.getSize();
		this.model = variantProduct.getModel();
		this.price = variantProduct.getPrice();

	}

}
