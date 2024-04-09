package com.finalproject.vdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.request.CartLineItemRequestDTO;
import com.finalproject.vdp.dto.response.CartLineItemResponseDTO;
import com.finalproject.vdp.exception.OrderNotFoundException;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.exception.VariantProductNotFoundException;
import com.finalproject.vdp.model.CartLineItem;
import com.finalproject.vdp.service.CartLineItemService;
import com.finalproject.vdp.utils.ResponseCode;

@RestController
@RequestMapping(path = "/cartlineitem")
public class CartLineItemController {
	@Autowired
	private CartLineItemService cartLineItemService;

	@PostMapping
	private ResponseEntity<?> addVariantProductToCartLineItem(
			@RequestBody CartLineItemRequestDTO cartLineItemRequestDTO) throws OrderNotFoundException, ValidationException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			List<CartLineItem> cartLineItem = this.cartLineItemService
					.addVariantProductToCartLineItem(userName, cartLineItemRequestDTO);
			List<CartLineItemResponseDTO> responseDTOs=this.cartLineItemService.mapToCartLineItemResponseDTOList(cartLineItem);
			return BaseResponseController.suceess(responseDTOs);
		} catch (VariantProductNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.VARIANT_PRODUCT_NOT_FOUND_EXCEPTION.getCode(),
					ResponseCode.VARIANT_PRODUCT_NOT_FOUND_EXCEPTION.getMessage());
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		}
	}
}
