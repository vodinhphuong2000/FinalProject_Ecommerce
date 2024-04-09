package com.finalproject.vdp.dto.response;

import java.util.List;
import java.util.Objects;

import com.finalproject.vdp.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AllVariantProductByIResponseDTO {
	private Integer productID;
	private String productName;
	private List<VariantProduct> variantProducts;

	public AllVariantProductByIResponseDTO(Product product) {
		this.productID = product.getProductID();
		this.productName = product.getProductName();
		this.variantProducts = VariantProduct.toVariantProducts(product);

	}

	@Data
	@AllArgsConstructor
	private static class VariantProduct {
		private Integer variant_productID;
		private String color;
		private String size;
		private String model;
		private Double price;

		public static List<VariantProduct> toVariantProducts(Product product) {
			return product.getVariantProducts().stream().filter(Objects::nonNull)
					.map(variantproduct -> new VariantProduct(variantproduct.getVariant_productID(),
							variantproduct.getColor(), variantproduct.getSize(), variantproduct.getModel(),
							variantproduct.getPrice()))
					.toList();
		}

	}
}
