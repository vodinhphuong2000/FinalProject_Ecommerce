
package com.finalproject.vdp.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.request.ProductRequestDTO;
import com.finalproject.vdp.dto.response.AllProductsByCategoryResponseDTO;
import com.finalproject.vdp.dto.response.ProductResponseDTO;
import com.finalproject.vdp.exception.CategoryNotFoundException;
import com.finalproject.vdp.exception.ProductNameExists;
import com.finalproject.vdp.exception.ProductNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Category;
import com.finalproject.vdp.model.Product;
import com.finalproject.vdp.repository.CategoryRepository;
import com.finalproject.vdp.service.ProductService;
import com.finalproject.vdp.utils.ResponseCode;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;
	@GetMapping(path = "/findById")
	public ResponseEntity<?> getProductByID(
			@RequestParam(name = "productID", required = false, defaultValue = "") int productID) {
		try {
			Product foundProduct = this.productService.findByID(productID);
			return BaseResponseController.suceess(new ProductResponseDTO(foundProduct));
		} catch (ProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
					ResponseCode.PRODUCT_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO productRequestDTO)
			throws ValidationException, ProductNameExists, CategoryNotFoundException {
		try {
			return BaseResponseController
					.suceess(new ProductResponseDTO(this.productService.addProduct(productRequestDTO)));
		} catch (CategoryNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.CATEGORY_NOT_FOUND.getCode(),
					ResponseCode.CATEGORY_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}

	}

	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody Product newProduct) {
		try {
			Product updateProduct = this.productService.updateProduct(newProduct);
			return BaseResponseController.suceess(updateProduct);
		} catch (ProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
					ResponseCode.PRODUCT_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}
	}

	@GetMapping(path = "/findByID")
	public ResponseEntity<?> getAllProductsByCategory(
			@RequestParam(name = "categoryID", required = false, defaultValue = "") Integer categoryID,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {
			Optional<Category> foundCategoryOptional=this.categoryRepository.findById(categoryID);
			Category category= foundCategoryOptional.get();
			category.setCategoryID(category.getCategoryID());
			category.setCategoryName(category.getCategoryName());
			category.setCategoryDescription(category.getCategoryDescription());
			Pageable pageable = PageRequest.of(page, size);
			 Page<Product> foundProductByCategory = this.productService.findAllProductByCategory(category, pageable);		
			return BaseResponseController.suceess(new AllProductsByCategoryResponseDTO(category,foundProductByCategory));
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

}
