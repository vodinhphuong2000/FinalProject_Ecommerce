package com.finalproject.vdp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.vdp.dto.request.CartLineItemRequestDTO;
import com.finalproject.vdp.dto.response.CartLineItemResponseDTO;
import com.finalproject.vdp.exception.OrderNotFoundException;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.exception.VariantProductNotFoundException;
import com.finalproject.vdp.model.Cart;
import com.finalproject.vdp.model.CartLineItem;
import com.finalproject.vdp.model.Oder;
import com.finalproject.vdp.model.User;
import com.finalproject.vdp.model.VariantProduct;
import com.finalproject.vdp.repository.CartLineItemRepository;
import com.finalproject.vdp.repository.CartRepository;
import com.finalproject.vdp.repository.OrderRepository;
import com.finalproject.vdp.repository.UserRepository;
import com.finalproject.vdp.repository.VariantProductRepository;

@Service
public class CartLineItemService {
	@Autowired
	private VariantProductRepository variantProductRepository;

	@Autowired
	private CartLineItemRepository cartLineItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderRepository oderOrderRepository;

	@Autowired
	private UserRepository userRepository;

	public void validateCartLineItem(CartLineItemRequestDTO cartLineItemRequestDTO) throws ValidationException {
		if (Objects.isNull(cartLineItemRequestDTO)) {
			throw new ValidationException("CartLineItem is null");
		}
		if (Objects.isNull(cartLineItemRequestDTO.getAdded_Date())
				|| cartLineItemRequestDTO.getAdded_Date().getTime() < 0) {
			throw new ValidationException("cartlineitem.added_date cannot be blank");
		}
		if (Objects.isNull(cartLineItemRequestDTO.getQuantity()) || cartLineItemRequestDTO.getQuantity() < 0) {
			throw new ValidationException("cartlineitem.price cannot be blank");
		}
		if (cartLineItemRequestDTO.isDeleted()) {
			throw new ValidationException("cartlineitem is already deleted");
		}
	}

	public List<CartLineItem> addVariantProductToCartLineItem(String userName,
			CartLineItemRequestDTO cartLineItemRequestDTO)
			throws UserNotFoundException, OrderNotFoundException, VariantProductNotFoundException, ValidationException {
		Optional<User> foundUserOptional = this.userRepository.findByUserName(userName);
		if (foundUserOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		User user = foundUserOptional.get();
		Optional<VariantProduct> foundVariantProductOptional = this.variantProductRepository
				.findById(cartLineItemRequestDTO.getVariant_productID());
		if (foundVariantProductOptional.isEmpty()) {
			throw new VariantProductNotFoundException();
		}
		VariantProduct variantProduct = foundVariantProductOptional.get();
		Optional<Oder> foundOrderOptional = this.oderOrderRepository.findById(cartLineItemRequestDTO.getOrderID());
		if (foundOrderOptional.isEmpty()) {
			throw new OrderNotFoundException();
		}
		Oder order = foundOrderOptional.get();
		Cart cart = user.getCarts();
		Optional<CartLineItem> existsCartLineItemOptional = cart.getCartLineItems().stream().filter(
				item -> item.getVariantProduct().getVariant_productID().equals(variantProduct.getVariant_productID()))
				.findFirst();
		if (existsCartLineItemOptional.isPresent()) {
			CartLineItem existingCartItem = existsCartLineItemOptional.get();
			existingCartItem.setQuantity(existingCartItem.getQuantity() + cartLineItemRequestDTO.getQuantity());
			double totalPrice = variantProduct.getPrice() * existingCartItem.getQuantity();
			existingCartItem.setTotal_price(totalPrice);
			cartLineItemRepository.save(existingCartItem);
		} else {
			CartLineItem newCartLineItem = new CartLineItem();
			validateCartLineItem(cartLineItemRequestDTO);
			newCartLineItem.setCart(cart);
			newCartLineItem.setVariantProduct(variantProduct);
			newCartLineItem.setOder(order);
			newCartLineItem.setQuantity(cartLineItemRequestDTO.getQuantity());
			newCartLineItem.setAdded_date(cartLineItemRequestDTO.getAdded_Date());
			newCartLineItem.setTotal_price(variantProduct.getPrice() * cartLineItemRequestDTO.getQuantity());
			newCartLineItem.setDeleted(false);
			cart.getCartLineItems().add(newCartLineItem);
		}

		this.cartRepository.save(cart);
		this.userRepository.save(user);
		return cart.getCartLineItems();
	}

	public CartLineItemResponseDTO mapToCartLineItemResponseDTO(CartLineItem cartLineItem, Double totalUnpaidPrice) {
		CartLineItemResponseDTO responseDTO = new CartLineItemResponseDTO();
		responseDTO.setCartline_itemID(cartLineItem.getCartline_itemID());
		responseDTO.setAdded_Date(cartLineItem.getAdded_date());
		responseDTO.setQuantity(cartLineItem.getQuantity());
		responseDTO.setTotal_Price(cartLineItem.getTotal_price());
		responseDTO.setTotalUnpaidPrice(totalUnpaidPrice);
		return responseDTO;
	}

	public Double calculateTotalUnpaidPrice() {

		List<CartLineItem> unpaidCartLineItems = cartLineItemRepository.findByIsDeleted(false);
		Double totalUnpaidPrice = 0.0;
		for (CartLineItem cartLineItem : unpaidCartLineItems) {
			totalUnpaidPrice += cartLineItem.getTotal_price();
		}

		return totalUnpaidPrice;
	}

	public List<CartLineItemResponseDTO> mapToCartLineItemResponseDTOList(List<CartLineItem> cartLineItem) {
		Double totalUnpaidPrice1 = calculateTotalUnpaidPrice();
		return cartLineItem.stream()
				.map(cartLineItems -> mapToCartLineItemResponseDTO(cartLineItems, totalUnpaidPrice1)).toList();
	}

}
