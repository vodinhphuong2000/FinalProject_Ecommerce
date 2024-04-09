package com.finalproject.vdp.dto.request;

import java.util.Date;

import com.finalproject.vdp.model.CartLineItem;
import lombok.Data;

@Data
public class CartLineItemRequestDTO {
	private Integer variant_productID;
	private Integer orderID;
	private Date added_Date;
	private Integer quantity;
	private boolean isDeleted;

	public CartLineItem toCartLineItemRequestDTO() {
		CartLineItem cartLineItem = new CartLineItem();
		cartLineItem.setAdded_date(this.added_Date);
		if (this.quantity != null) {
			cartLineItem.setQuantity(this.quantity);
		} else {
			cartLineItem.setQuantity(0);
		}
		cartLineItem.setDeleted(this.isDeleted);
		return cartLineItem;
	}

}
