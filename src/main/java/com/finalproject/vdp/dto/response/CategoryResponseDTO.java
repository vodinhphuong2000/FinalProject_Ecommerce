package com.finalproject.vdp.dto.response;

import com.finalproject.vdp.model.Category;

import lombok.Data;

@Data
public class CategoryResponseDTO {
	private Integer categoryID;
	private String categoryName;
	private String categoryDescription;
	
	public CategoryResponseDTO(Category category) {
		this.categoryID=category.getCategoryID();
		this.categoryName=category.getCategoryName();
		this.categoryDescription=category.getCategoryDescription();
	}

}
