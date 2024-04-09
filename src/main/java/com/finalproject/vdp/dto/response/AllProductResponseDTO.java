package com.finalproject.vdp.dto.response;

import lombok.Data;

@Data
public class AllProductResponseDTO {
	private Integer productID;
	private String productName;

	public AllProductResponseDTO(Integer productID, String productName) {
		this.productID = productID;
		this.productName = productName;
	}
}
