package com.finalproject.vdp.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class CartLineItemResponseDTO {
	private Integer cartline_itemID;
	private Integer quantity;
	private Double total_Price;
	private Date added_Date;
	private boolean isDeleted;
	private Double totalUnpaidPrice;

	public CartLineItemResponseDTO() {

	}
	
	

//	public CartLineItemResponseDTO(CartLineItem cartLineItem) {
//		this.cartline_itemID = cartLineItem.getCartline_itemID();
//		this.quantity = cartLineItem.getQuantity();
//		this.total_Price = cartLineItem.getTotal_price();
//		this.added_Date = cartLineItem.getAdded_date();
//		this.isDeleted = cartLineItem.isDeleted();
//		
//	}
//
//	public CartLineItemResponseDTO(Integer cartline_itemID, Integer quantity, Double total_Price, Date added_Date,
//			boolean isDeleted, Double totalUnpaidPrice) {
//		super();
//		this.cartline_itemID = cartline_itemID;
//		this.quantity = quantity;
//		this.total_Price = total_Price;
//		this.added_Date = added_Date;
//		this.isDeleted = isDeleted;
//		this.totalUnpaidPrice = totalUnpaidPrice;
//	}

}
