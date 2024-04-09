package com.finalproject.vdp.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.finalproject.vdp.model.Category;
import com.finalproject.vdp.model.Product;

import lombok.Data;

@Data
public class AllProductsByCategoryResponseDTO {
	private Integer categoryID;
	private String categoryName;
	private String categoryDescription;
	private List<AllProductResponseDTO> products;

	public AllProductsByCategoryResponseDTO(Category category,Page<Product> product) {
		this.categoryID = category.getCategoryID();
		this.categoryName = category.getCategoryName();
		this.categoryDescription = category.getCategoryDescription();
		this.products = mapToProductDTOList(product);
	}

	private List<AllProductResponseDTO> mapToProductDTOList(Page<Product> product) {
		return product.stream().map(p -> new AllProductResponseDTO(p.getProductID(), p.getProductName()))
				.collect(Collectors.toList());
	}
}

