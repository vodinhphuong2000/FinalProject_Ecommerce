package com.finalproject.vdp.dto.request;

import java.util.Date;

import com.finalproject.vdp.model.Oder;

import lombok.Data;

@Data
public class OrderRequestDTO {
	private String address;
	private Date deliveryTime;

	public Oder toOderRequestDTO() {
		Oder oders = new Oder();
		oders.setAddress(address);
		oders.setDeliveryTime(deliveryTime);
		return oders;
	}
}
