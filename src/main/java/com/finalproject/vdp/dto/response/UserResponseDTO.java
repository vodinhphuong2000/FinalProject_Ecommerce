package com.finalproject.vdp.dto.response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.finalproject.vdp.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserResponseDTO {
	private Integer userID;
	private String userName;
	private String password;
	private String fullName;
	private String phone;
	private Date birthDate;
//	

	public UserResponseDTO(User user) {
		this.userID = user.getUserID();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.fullName = user.getFullName();
		this.phone = user.getPhone();
		this.birthDate = user.getBirthDate();
//		
	}

//
}
