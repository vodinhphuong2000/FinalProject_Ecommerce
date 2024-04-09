package com.finalproject.vdp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.finalproject.vdp.dto.request.UserRequestDTO;
import com.finalproject.vdp.exception.UserAlreadyExistsException;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Cart;
import com.finalproject.vdp.model.User;
import com.finalproject.vdp.repository.RoleRepository;
import com.finalproject.vdp.repository.UpdateUserRequestDTO;
import com.finalproject.vdp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private void validateUser(UpdateUserRequestDTO user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null ");
		}
		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank ");
		}
		if (Objects.isNull(user.getPassword()) || user.getPassword().isBlank()) {
			throw new ValidationException("user.passWord cannot be blank ");
		}
		if (Objects.isNull(user.getFullName()) || user.getFullName().isBlank()) {
			throw new ValidationException("user.fullName cannot be blank ");
		}
		if (Objects.isNull(user.getPhone()) || user.getPhone().isBlank()) {
			throw new ValidationException("user.phone invalid phone number ");
		}
		if (Objects.isNull(user.getBirthDate()) || user.getBirthDate().getTime() < 0) {
			throw new ValidationException("user.birthDate must be positive ");
		}

	}

	private void validateUser(UserRequestDTO user) throws ValidationException {
		if (Objects.isNull(user)) {
			throw new ValidationException("user is null ");
		}
		if (Objects.isNull(user.getUserName()) || user.getUserName().isBlank()) {
			throw new ValidationException("user.userName cannot be blank ");
		}
		if (Objects.isNull(user.getPassword()) || user.getPassword().isBlank()) {
			throw new ValidationException("user.passWord cannot be blank ");
		}
		if (Objects.isNull(user.getFullName()) || user.getFullName().isBlank()) {
			throw new ValidationException("user.fullName cannot be blank ");
		}
		if (Objects.isNull(user.getPhone()) || user.getPhone().isBlank()) {
			throw new ValidationException("user.phone invalid phone number ");
		}
		if (Objects.isNull(user.getBirthDate()) || user.getBirthDate().getTime() < 0) {
			throw new ValidationException("user.birthDate must be positive ");
		}

	}

	public User addUser(UserRequestDTO user) throws ValidationException, UserAlreadyExistsException {
		validateUser(user);
		Optional<User> foundUserName = this.userRepository.findByUserName(user.getUserName());
		if (foundUserName.isPresent()) {
			throw new UserAlreadyExistsException();
		}
		User insertedUser = user.toUser();
		insertedUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
		insertedUser.setRoles(List.of(this.roleRepository.findById(1).get()));
		Cart cart = new Cart();
		cart.setUsers(insertedUser);
		insertedUser.setCarts(cart);
		return this.userRepository.save(insertedUser);

	}

	public User updateUserLogin(String userName, UpdateUserRequestDTO newUser)
			throws ValidationException, UserNotFoundException,UsernameNotFoundException{

		Optional<User> foundUser = this.userRepository.findByUserName(userName);
		if (foundUser.isEmpty()) {
			throw new UserNotFoundException();
		}
		User users = foundUser.get();
		validateUser(newUser);
		if (!newUser.getUserName().equals(userName)) {
			throw new UsernameNotFoundException(userName);
		}
		users.setUserName(newUser.getUserName());
		users.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
		users.setFullName(newUser.getFullName());
		users.setPhone(newUser.getPhone());
		users.setBirthDate(newUser.getBirthDate());
		return this.userRepository.save(users);
	}
}
