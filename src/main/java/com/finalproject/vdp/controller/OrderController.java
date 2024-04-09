package com.finalproject.vdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.request.OrderRequestDTO;
import com.finalproject.vdp.dto.response.OrderResponseDTO;
import com.finalproject.vdp.exception.CartLineItemNotFoundException;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.model.Oder;
import com.finalproject.vdp.service.OrderService;
import com.finalproject.vdp.utils.ResponseCode;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<?> addOderToCartLineItem(@RequestBody OrderRequestDTO orderRequestDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			Oder oders = this.orderService.createOder(userName, orderRequestDTO);
			return BaseResponseController.suceess(new OrderResponseDTO(oders));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (CartLineItemNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.CART_LINE_ITEM_NOT_FOUND_EXCEPTION.getCode(),
					ResponseCode.CART_LINE_ITEM_NOT_FOUND_EXCEPTION.getMessage());
		}
	}

}
