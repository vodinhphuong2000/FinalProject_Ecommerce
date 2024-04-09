package com.finalproject.vdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.request.ProductToVariantProductRequestDTO;
import com.finalproject.vdp.dto.request.VariantProductRequestDTO;
import com.finalproject.vdp.dto.response.AllVariantProductByIResponseDTO;
import com.finalproject.vdp.dto.response.ProductToVariantProductResponseDTO;
import com.finalproject.vdp.dto.response.VariantProductResponseDTO;
import com.finalproject.vdp.exception.ProductNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.exception.VariantProductNotFoundException;
import com.finalproject.vdp.model.Product;
import com.finalproject.vdp.model.VariantProduct;
import com.finalproject.vdp.service.VariantProductService;
import com.finalproject.vdp.utils.ResponseCode;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/variantproduct")
public class VariantProductController {
	@Autowired
	private VariantProductService variantProductService;

	@GetMapping(path = "/findByID")
	public ResponseEntity<?> getAllVariantProductByProduct(
			@RequestParam(name = "productID", required = false, defaultValue = "0") Integer productID) {
		try {
			Product foundAllVariantProducts = this.variantProductService.getAllVariantProductByProductID(productID);
			foundAllVariantProducts.getVariantProducts();
			return BaseResponseController.suceess(new AllVariantProductByIResponseDTO(foundAllVariantProducts));

		} catch (ProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
					ResponseCode.PRODUCT_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> addVariantProduct(@RequestBody VariantProductRequestDTO variantProductRequestDTO)
			throws ValidationException {
		try {
			return BaseResponseController.suceess(new VariantProductResponseDTO(
					this.variantProductService.addVariantProduct(variantProductRequestDTO)));

		} catch (ProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
					ResponseCode.PRODUCT_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody VariantProduct newVariantProduct) {
		try {
			VariantProduct updateVariantProduct = this.variantProductService.updateVariantProduct(newVariantProduct);
			return BaseResponseController.suceess(updateVariantProduct);
		} catch (VariantProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.VARIANT_PRODUCT_NOT_FOUND_EXCEPTION.getCode(),
					ResponseCode.VARIANT_PRODUCT_NOT_FOUND_EXCEPTION.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}
	}

	@PostMapping(path = "/addProduct")
	public ResponseEntity<?> addProductToVariantProduct(
			@RequestParam(name = "variant_productID", required = false, defaultValue = "") Integer variant_productID,
			@RequestBody ProductToVariantProductRequestDTO productToVariantProductRequestDTO) {
		try {
			return BaseResponseController.suceess(new ProductToVariantProductResponseDTO(this.variantProductService
					.addProductToVariantProduct(variant_productID, productToVariantProductRequestDTO)));
		} catch (VariantProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.VARIANT_PRODUCT_NOT_FOUND_EXCEPTION.getCode(),
					ResponseCode.VARIANT_PRODUCT_NOT_FOUND_EXCEPTION.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}
	}
}
