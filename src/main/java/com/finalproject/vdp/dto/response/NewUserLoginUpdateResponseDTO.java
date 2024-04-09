package com.finalproject.vdp.dto.response;

import java.util.Date;

import com.finalproject.vdp.model.User;

import lombok.Data;

@Data
public class NewUserLoginUpdateResponseDTO {
	private String userName;
	private String password;
	private String fullName;
	private String phone;
	private Date birthDate;

	public NewUserLoginUpdateResponseDTO(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.fullName = user.getFullName();
		this.phone = user.getPhone();
		this.birthDate = user.getBirthDate();
	}

}