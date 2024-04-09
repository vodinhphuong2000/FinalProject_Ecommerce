package com.finalproject.vdp.dto.request;

import com.finalproject.vdp.model.Category;

import lombok.Data;

@Data
public class CategoryRequestDTO {
	private String categoryName;
	private String categoryDescription;

	public Category toCategory() {
		Category category = new Category();
		category.setCategoryName(this.categoryName);
		category.setCategoryDescription(this.categoryDescription);
		return category;
	}
}
