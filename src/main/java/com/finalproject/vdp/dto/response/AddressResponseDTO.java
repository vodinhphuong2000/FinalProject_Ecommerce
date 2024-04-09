package com.finalproject.vdp.dto.response;

import java.util.List;
import java.util.Objects;

import com.finalproject.vdp.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddressResponseDTO {
	private Integer userID;
	private String userName;
	private List<Address> addresses;

	public AddressResponseDTO(User user) {
		this.userID=user.getUserID();
		this.userName = user.getUserName();
		this.addresses = Address.toAddress(user);

	}

	@Data
	@AllArgsConstructor
	private static class Address {
		private Integer addressId;
		private String addressName;

		public static List<Address> toAddress(User user) {
			return user.getAddress().stream().filter(Objects::nonNull)
					.map(address -> new Address(address.getAddressID(), address.getAddressName())).toList();
		}
	}
}
