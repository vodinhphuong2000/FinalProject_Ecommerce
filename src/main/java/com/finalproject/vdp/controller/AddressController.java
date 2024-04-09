package com.finalproject.vdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.response.AddressResponseDTO;
import com.finalproject.vdp.dto.response.GetAddressByUserResponseDTO;
import com.finalproject.vdp.dto.response.UpdateAddressResponseDTO;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Address;
import com.finalproject.vdp.model.User;
import com.finalproject.vdp.service.AddressService;
import com.finalproject.vdp.utils.ResponseCode;

@RestController
@RequestMapping(path = "/address")
public class AddressController {
	@Autowired
	private AddressService addressService;


	@GetMapping
	public ResponseEntity<?> getAddressByUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			List<GetAddressByUserResponseDTO> foundAddressByUser = this.addressService.findAddressByUser(userName);
			return BaseResponseController.suceess(foundAddressByUser);
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(), e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> addAddress(@RequestBody Address addressRequestDTO)
			throws ValidationException, UserNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			User user = this.addressService.addAddress(userName, addressRequestDTO);
			return BaseResponseController.suceess(new AddressResponseDTO(user));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} catch (ValidationException e) {
			return BaseResponseController.fail(ResponseCode.VALIDATION_EXCEPTION.getCode(),
					ResponseCode.VALIDATION_EXCEPTION.getMessage());
		}

	}

	@PutMapping
	public ResponseEntity<?> updateAddress(@RequestBody Address newAddress) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			Address updateAddress = this.addressService.updateAddress(userName,newAddress);
			return BaseResponseController.suceess(new UpdateAddressResponseDTO(updateAddress));
		} catch (UserNotFoundException e) {
			return BaseResponseController.fail(ResponseCode.USER_NOT_FOUND.getCode(),
					ResponseCode.USER_NOT_FOUND.getMessage());
		} 
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteAddress(@RequestParam(name = "addressID",required = false,defaultValue = "") Integer addressID) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			return BaseResponseController.suceess(this.addressService.deleteAddress(addressID, userName) );

		} catch (Exception e) {
			return BaseResponseController.fail(ResponseCode.ADDRESS_NOT_FOUND.getCode(),
					ResponseCode.ADDRESS_NOT_FOUND.getMessage());
		}
	}

}
