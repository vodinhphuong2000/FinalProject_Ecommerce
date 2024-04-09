package com.finalproject.vdp.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	SUCCESS("success", "success"), 
	
	USER_ALREADY_EXISTS("user.exists", "user already exists"),
	
	USER_NOT_FOUND("user.notfound", "user not found"),
	
	CATEGORY_ALREADY_EXISTS("category.exists", "category already exists"),
	
	CATEGORY_NOT_FOUND("category.notfound", "category not found"),
	
	PRODUCT_NOT_FOUND("product.notfound", "product not found"),
	
	VALIDATION_EXCEPTION("validation", null),
	
	USERNAME_ALREADY_EXISTS("username.exists", "username already exists"),
	
	ADDRESS_NOT_FOUND("address.notfound", "address not found"),
	
	ADDRESSNAME_EXISTS("addressname.exists", "addressname already exists"),
	
	VARIANT_PRODUCT_NOT_FOUND_EXCEPTION("variantproduct.notfound", "variantproduct not found"),
	
	CART_NOT_FOUND_EXCEPTION("cart.notfound", "cart not found"),
	
	ORDER_NOT_FOUND_EXCEPTION("order.notfound", "order not found"),
	
	CART_LINE_ITEM_NOT_FOUND_EXCEPTION("cartlineitem.notfound", "cartlineitem not found");

	private String code;
	private String message;
}
