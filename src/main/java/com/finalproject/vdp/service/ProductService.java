package com.finalproject.vdp.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.vdp.dto.request.ProductRequestDTO;
import com.finalproject.vdp.exception.CategoryNotFoundException;
import com.finalproject.vdp.exception.ProductNameExists;
import com.finalproject.vdp.exception.ProductNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Category;
import com.finalproject.vdp.model.Product;
import com.finalproject.vdp.repository.CategoryRepository;
import com.finalproject.vdp.repository.ProductReponsitory;

@Service
public class ProductService {
	@Autowired
	private ProductReponsitory productReponsitory;

	@Autowired
	private CategoryRepository categoryRepository;

	private void validateProduct(Product product) throws ValidationException {
		if (Objects.isNull(product)) {
			throw new ValidationException("product is null");
		}
		if (Objects.isNull(product.getProductName()) || product.getProductName().isBlank()) {
			throw new ValidationException("category.name cannot be blank");
		}
	}

	private void validateProduct(ProductRequestDTO product) throws ValidationException {
		if (Objects.isNull(product)) {
			throw new ValidationException("product is null");
		}
		if (Objects.isNull(product.getProductName()) || product.getProductName().isBlank()) {
			throw new ValidationException("category.name cannot be blank");
		}
	}

	public Product addProduct(ProductRequestDTO productRequestDTO)
			throws CategoryNotFoundException, ValidationException, ProductNameExists {
		this.validateProduct(productRequestDTO);

		Optional<Category> foundCategoryOptional = this.categoryRepository.findById(productRequestDTO.getCategoryID());
		if (foundCategoryOptional.isEmpty()) {
			throw new CategoryNotFoundException();
		}
		Category foundCategory = foundCategoryOptional.get();
		String productName = productRequestDTO.getProductName();
		boolean isProductNameExists = foundCategory.getProducts().stream()
				.anyMatch(product -> product.getProductName().equalsIgnoreCase(productName));
		if (isProductNameExists) {
			throw new ProductNameExists();
		}

		Product product = productRequestDTO.toProduct();
		product.setCategory(foundCategory);
		return this.productReponsitory.save(product);
	}

	public Product findByID(Integer productID) throws ValidationException, ProductNotFoundException {
		if (Objects.isNull(productID)) {
			throw new ValidationException("product.id must be postive");
		}
		Optional<Product> foundProduct = this.productReponsitory.findById(productID);
		if (foundProduct.isEmpty()) {
			throw new ProductNotFoundException();
		}
		return foundProduct.get();
	}

	public Product updateProduct(Product newProduct) throws ProductNotFoundException, ValidationException {
		validateProduct(newProduct);
		Optional<Product> foundProductOptional = this.productReponsitory.findById(newProduct.getProductID());
		if (foundProductOptional.isEmpty()) {
			throw new ProductNotFoundException();
		}
		return this.productReponsitory.save(newProduct);
	}
	 public Page<Product> findAllProductByCategory(Category categoryID, Pageable pageable) throws ValidationException {
		 if (Objects.isNull(categoryID)|| categoryID.getCategoryID()< 0) {
			 throw new ValidationException("category.id must be postive ");
		 }
	        return this.productReponsitory.findByCategory(categoryID, pageable);
	    }
}
