package com.finalproject.vdp.dto.request;

import com.finalproject.vdp.model.Address;

import lombok.Data;

@Data
public class AddressRequestDTO {
	private Integer userID;
	private String addressName;

	public Address toAddress() {
		Address address = new Address();
		address.setAddressName(this.addressName);
		return address;
	}
}
