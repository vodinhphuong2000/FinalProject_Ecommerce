package com.finalproject.vdp.repository;

import java.util.Date;

import com.finalproject.vdp.model.User;

import lombok.Data;
@Data
public class UpdateUserRequestDTO {
	private String userName;
	private String password;
	private String fullName;
	private String phone;
	private Date birthDate;

	public User toUser() {
		User users=new User();
		users.setUserName(userName);
		users.setPassword(password);
		users.setFullName(fullName);
		users.setPhone(phone);
		users.setBirthDate(birthDate);
		return users;
	}
}
