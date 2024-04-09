package com.finalproject.vdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.request.CategoryRequestDTO;
import com.finalproject.vdp.dto.response.CategoryResponseDTO;
import com.finalproject.vdp.exception.CategoryAlreadyExistsException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Category;
import com.finalproject.vdp.service.CategoryService;
import com.finalproject.vdp.utils.ResponseCode;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(path="/all")
	public ResponseEntity<?> getAllCategory() {
		return BaseResponseController.suceess(this.categoryService.getAll());
	}

//@PreAuthorize
	@PostMapping(path = "/add")
	public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO category) {
		try {
			Category addCategory = this.categoryService.addCategory(category);
			return BaseResponseController.suceess(new CategoryResponseDTO(addCategory));
		} catch (CategoryAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.CATEGORY_ALREADY_EXISTS.getCode(),
					ResponseCode.CATEGORY_ALREADY_EXISTS.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}

	}

}
