package com.finalproject.vdp.dto.response;

import java.util.Date;

import com.finalproject.vdp.model.Oder;

import lombok.Data;

@Data
public class OrderResponseDTO {
	private Integer orderID;
	private String address;
	private Date deliveryTime;
	private Double totalPrice;

	public OrderResponseDTO(Oder order) {
		this.orderID = order.getOderID();
		this.address = order.getAddress();
		this.deliveryTime = order.getDeliveryTime();
		this.totalPrice = order.getTotalPrice();
	}
}
