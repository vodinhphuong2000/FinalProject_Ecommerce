package com.finalproject.vdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.request.UserRequestDTO;
import com.finalproject.vdp.dto.response.NewUserLoginUpdateResponseDTO;
import com.finalproject.vdp.dto.response.UserResponseDTO;
import com.finalproject.vdp.exception.UserAlreadyExistsException;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.User;
import com.finalproject.vdp.repository.UpdateUserRequestDTO;
import com.finalproject.vdp.service.UserService;
import com.finalproject.vdp.utils.ResponseCode;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getUserLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		return BaseResponseController.suceess(userName);
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserRequestDTO userRequestDTO) {
		try {
			User addUsers = this.userService.addUser(userRequestDTO);
			return BaseResponseController.suceess(new UserResponseDTO(addUsers));
		} catch (UserAlreadyExistsException e) {
			return BaseResponseController.fail(ResponseCode.USER_ALREADY_EXISTS.getCode(),
					ResponseCode.USER_ALREADY_EXISTS.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}
	}

	@PutMapping()
	public ResponseEntity<?> updateUserLogin(@RequestBody UpdateUserRequestDTO newUser) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			User updateUserLogin = this.userService.updateUserLogin(userName,newUser);
			return BaseResponseController.suceess(new NewUserLoginUpdateResponseDTO(updateUserLogin));
		} catch (UsernameNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USERNAME_ALREADY_EXISTS.getCode(),
					ResponseCode.USERNAME_ALREADY_EXISTS.getMessage());
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}
	}

}
