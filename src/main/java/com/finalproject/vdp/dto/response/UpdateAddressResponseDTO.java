package com.finalproject.vdp.dto.response;

import com.finalproject.vdp.model.Address;

import lombok.Data;

@Data
public class UpdateAddressResponseDTO {
	private Integer addressID;
	private String addressName;

	public UpdateAddressResponseDTO(Address address) {
		this.addressID = address.getAddressID();
		this.addressName = address.getAddressName();
	}
}
