package com.finalproject.vdp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finalproject.vdp.model.User;
import com.finalproject.vdp.repository.UserRepository;


@Service
public class CustomUserServiceDetail implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		if (user.isPresent()) {
			return new CustomUserDetail(user.get());
		} else {
			throw new RuntimeException("User not found");
		}
	}

}
