package com.finalproject.vdp.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.vdp.dto.request.ProductToVariantProductRequestDTO;
import com.finalproject.vdp.dto.request.VariantProductRequestDTO;
import com.finalproject.vdp.exception.ProductNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.exception.VariantProductNotFoundException;
import com.finalproject.vdp.model.Product;
import com.finalproject.vdp.model.VariantProduct;
import com.finalproject.vdp.repository.ProductReponsitory;
import com.finalproject.vdp.repository.VariantProductRepository;

@Service
public class VariantProductService {
	@Autowired
	private ProductReponsitory productReponsitory;

	@Autowired
	private VariantProductRepository variantProductRepository;
/**
 * Validate VarianProduct to check whether the input value is wrong or not. If incorrect, report an error
 * @param variantProduct
 * @throws ValidationException
 */
	public void validateVariantProduct(VariantProduct variantProduct) throws ValidationException {
		if (Objects.isNull(variantProduct)) {
			throw new ValidationException("VariantProduct is null");
		}
		if (Objects.isNull(variantProduct.getColor()) || variantProduct.getColor().isBlank()) {
			throw new ValidationException("variantproduct.color cannot be blank");
		}
		if (Objects.isNull(variantProduct.getSize()) || variantProduct.getSize().isBlank()) {
			throw new ValidationException("variantproduct.size cannot be blank");
		}
		if (Objects.isNull(variantProduct.getModel()) || variantProduct.getModel().isBlank()) {
			throw new ValidationException("variantproduct.model cannot be blank");
		}
		if (Objects.isNull(variantProduct.getPrice()) || variantProduct.getPrice() < 0) {
			throw new ValidationException("variantproduct.price cannot be blank");
		}
	}

	public void validateVariantProduct(VariantProductRequestDTO variantProductRequestDTO) throws ValidationException {
		if (Objects.isNull(variantProductRequestDTO)) {
			throw new ValidationException("VariantProduct is null");
		}
		if (Objects.isNull(variantProductRequestDTO.getColor()) || variantProductRequestDTO.getColor().isBlank()) {
			throw new ValidationException("variantproduct.color cannot be blank");
		}
		if (Objects.isNull(variantProductRequestDTO.getSize()) || variantProductRequestDTO.getSize().isBlank()) {
			throw new ValidationException("variantproduct.size cannot be blank");
		}
		if (Objects.isNull(variantProductRequestDTO.getModel()) || variantProductRequestDTO.getModel().isBlank()) {
			throw new ValidationException("variantproduct.model cannot be blank");
		}
		if (Objects.isNull(variantProductRequestDTO.getPrice()) || variantProductRequestDTO.getPrice() < 0) {
			throw new ValidationException("variantproduct.price cannot be blank");
		}
	}

	public void validateProduct(ProductToVariantProductRequestDTO variantProductToVariantProductRequestDTO)
			throws ValidationException {
		if (Objects.isNull(variantProductToVariantProductRequestDTO)) {
			throw new ValidationException("Product is null");
		}
		if (Objects.isNull(variantProductToVariantProductRequestDTO.getProductName())
				|| variantProductToVariantProductRequestDTO.getProductName().isBlank()) {
			throw new ValidationException("product.name cannot be blank");
		}
	}

	public VariantProduct addVariantProduct(VariantProductRequestDTO variantProductRequestDTO)
			throws ValidationException, ProductNotFoundException {
		this.validateVariantProduct(variantProductRequestDTO);
		Optional<Product> foundProductOptional = this.productReponsitory
				.findById(variantProductRequestDTO.getProductID());
		if (foundProductOptional.isEmpty()) {
			throw new ProductNotFoundException();
		}
		Product product = foundProductOptional.get();
		VariantProduct variantProduct = new VariantProduct();
		variantProduct.setColor(variantProductRequestDTO.getColor());
		variantProduct.setModel(variantProductRequestDTO.getModel());
		variantProduct.setSize(variantProductRequestDTO.getSize());
		variantProduct.setPrice(variantProductRequestDTO.getPrice());
		variantProduct.setProducts(product);
		return this.variantProductRepository.save(variantProduct);

	}

	public Product getAllVariantProductByProductID(Integer productID)
			throws ValidationException, ProductNotFoundException {
		if (Objects.isNull(productID) || productID < 0) {
			throw new ValidationException("product.id must be positive");
		}
		Optional<Product> foundProductOptional = this.productReponsitory.findById(productID);
		if (foundProductOptional.isEmpty()) {
			throw new ProductNotFoundException();
		}
	
		return foundProductOptional.get();
	}

	public VariantProduct updateVariantProduct(VariantProduct newVariantProduct)
			throws VariantProductNotFoundException, ValidationException {
		validateVariantProduct(newVariantProduct);
		Optional<VariantProduct> foundVariantProductOptional = this.variantProductRepository
				.findById(newVariantProduct.getVariant_productID());
		if (foundVariantProductOptional.isEmpty()) {
			throw new VariantProductNotFoundException();
		}
		return this.variantProductRepository.save(newVariantProduct);
	}

	public VariantProduct addProductToVariantProduct(Integer variant_productID,
			ProductToVariantProductRequestDTO productToVariantProductRequestDTO)
			throws VariantProductNotFoundException, ValidationException {
		validateProduct(productToVariantProductRequestDTO);
		Optional<VariantProduct> foundVariantProductOptional = this.variantProductRepository
				.findById(variant_productID);
		if (foundVariantProductOptional.isEmpty()) {
			throw new VariantProductNotFoundException();
		}
		VariantProduct foundVariantProduct = foundVariantProductOptional.get();
		Product product = productToVariantProductRequestDTO.toProduct();
		foundVariantProduct.setProducts(product);
		return this.variantProductRepository.save(foundVariantProduct);
	}
}
