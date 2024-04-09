package com.finalproject.vdp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.vdp.dto.request.CategoryRequestDTO;
import com.finalproject.vdp.dto.response.CategoryResponseDTO;
import com.finalproject.vdp.exception.CategoryAlreadyExistsException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Category;
import com.finalproject.vdp.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;


	private void validateCategory(CategoryRequestDTO category) throws ValidationException {
		if (Objects.isNull(category)) {
			throw new ValidationException("category is null");
		}
		if (Objects.isNull(category.getCategoryName()) || category.getCategoryName().isBlank()) {
			throw new ValidationException("category.name cannot be blank");
		}
		if (Objects.isNull(category.getCategoryDescription()) || category.getCategoryDescription().isBlank()) {
			throw new ValidationException("category.description cannot be blank");
		}

	}
	/**
	 * Service 
	 * @return All Category 
	 */
	public List<CategoryResponseDTO> getAll() {
		return this.categoryRepository.findAll().stream().map(CategoryResponseDTO::new).toList();
	}
	public Category addCategory(CategoryRequestDTO category)
			throws ValidationException, CategoryAlreadyExistsException {
		this.validateCategory(category);

		Optional<Category> foundCategory = this.categoryRepository.findByCategoryName(category.getCategoryName());
		if (foundCategory.isPresent()) {
			throw new CategoryAlreadyExistsException();
		}
		Category insertedCategory = category.toCategory();
		insertedCategory.setCategoryName(category.getCategoryName());
		insertedCategory.setCategoryDescription(category.getCategoryDescription());
		return this.categoryRepository.save(insertedCategory);
	}

}
