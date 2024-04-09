package com.finalproject.vdp.dto.response;

import java.util.Objects;

import com.finalproject.vdp.model.Category;
import com.finalproject.vdp.model.Product;
import lombok.Data;

@Data
public class ProductResponseDTO {
	private Integer productID;
	private String productName;
	private Integer categoryID;
	private String categoryName;
	public ProductResponseDTO(Product product) {
		this.productID = product.getProductID();
		this.productName = product.getProductName();
		Category category = product.getCategory();
		if(Objects.nonNull(category)) {
			this.categoryID= category.getCategoryID();
			this.categoryName=category.getCategoryName();
		}
	}
}
