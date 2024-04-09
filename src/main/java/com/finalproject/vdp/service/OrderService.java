package com.finalproject.vdp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.vdp.dto.request.OrderRequestDTO;
import com.finalproject.vdp.exception.CartLineItemNotFoundException;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Cart;
import com.finalproject.vdp.model.CartLineItem;
import com.finalproject.vdp.model.Oder;
import com.finalproject.vdp.model.User;
import com.finalproject.vdp.repository.CartLineItemRepository;
import com.finalproject.vdp.repository.CartRepository;
import com.finalproject.vdp.repository.OrderRepository;
import com.finalproject.vdp.repository.UserRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository oderRepository;

	@Autowired
	private CartLineItemRepository cartLineItemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	public void validateOder(OrderRequestDTO orderRequestDTO) throws ValidationException {
		if (Objects.isNull(orderRequestDTO)) {
			throw new ValidationException("order is null");
		}
		if (Objects.isNull(orderRequestDTO.getAddress()) || orderRequestDTO.getAddress().isBlank()) {
			throw new ValidationException("address.order cannot be blank");
		}
		if (Objects.isNull(orderRequestDTO.getDeliveryTime()) || orderRequestDTO.getDeliveryTime().getTime() < 0) {
			throw new ValidationException("deleverytime.order must be positive");
		}

	}

	public Oder createOder(String userName, OrderRequestDTO oderRequestDTO)
			throws UserNotFoundException, CartLineItemNotFoundException {
		Optional<User> foundUserOptional = this.userRepository.findByUserName(userName);
		if (foundUserOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		User user = foundUserOptional.get();
		Cart cart = user.getCarts();
		if (cart == null || user.getCarts().getCartLineItems().isEmpty()) {
			throw new CartLineItemNotFoundException();
		}
		List<CartLineItem> cartLineItems = user.getCarts().getCartLineItems().stream().filter(item -> !item.isDeleted())
				.toList();
		double totalPrice = cartLineItems.stream().mapToDouble(item -> item.getTotal_price()).sum();

		Oder oders = new Oder();
		oders.setCartLineItems(cartLineItems);
		oders.setAddress(oderRequestDTO.getAddress());
		oders.setDeliveryTime(oderRequestDTO.getDeliveryTime());
		oders.setTotalPrice(totalPrice);
//		this.oderRepository.save(oders);
//		Double totalPrice = 0.0;
//		for (CartLineItem cartLineItem : cartLineItems) {
//			oders.setCartLineItems(cartLineItems);
//			totalPrice += cartLineItem.getTotal_price();
//		}
		for (CartLineItem cartLineItem : cartLineItems) {
			cartLineItem.setDeleted(true);
			this.cartLineItemRepository.save(cartLineItem);
		}
		this.cartRepository.save(cart);
		this.userRepository.save(user);
		return this.oderRepository.save(oders);
	}
}
