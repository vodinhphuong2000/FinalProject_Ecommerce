package com.finalproject.vdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.vdp.dto.request.AuthRequestDTO;
import com.finalproject.vdp.dto.response.AuthResponseDTO;
import com.finalproject.vdp.utils.JwtUtils;


@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseEntity<?> generateToken(@RequestBody AuthRequestDTO authRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
		String token = JwtUtils.generateToken(authRequestDTO.getUsername());
		AuthResponseDTO authResponse = new AuthResponseDTO(token, "Đăng nhập thành công");
		return BaseResponseController.suceess(authResponse);

	}
	
}