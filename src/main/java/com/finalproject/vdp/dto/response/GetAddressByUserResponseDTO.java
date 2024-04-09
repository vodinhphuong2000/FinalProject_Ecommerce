package com.finalproject.vdp.dto.response;

import lombok.Data;

@Data
public class GetAddressByUserResponseDTO {
	private String userName;
	private Integer addressID;
	private String addressName;

	public GetAddressByUserResponseDTO(String userName, Integer addressID, String addressName) {

		this.userName = userName;
		this.addressID = addressID;
		this.addressName = addressName;
	}

}
