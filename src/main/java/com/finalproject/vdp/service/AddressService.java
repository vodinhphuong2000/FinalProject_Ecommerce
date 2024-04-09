package com.finalproject.vdp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.vdp.dto.request.AddressRequestDTO;
import com.finalproject.vdp.dto.response.GetAddressByUserResponseDTO;
import com.finalproject.vdp.exception.AddressNotFoundException;
import com.finalproject.vdp.exception.UserNotFoundException;
import com.finalproject.vdp.exception.ValidationException;
import com.finalproject.vdp.model.Address;
import com.finalproject.vdp.model.User;
import com.finalproject.vdp.repository.AddressRepository;
import com.finalproject.vdp.repository.UserRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;

	public void validateAddress(Address address) throws ValidationException {
		if (Objects.isNull(address)) {
			throw new ValidationException("address is null");
		}
		if (Objects.isNull(address.getAddressName()) || address.getAddressName().isBlank()) {
			throw new ValidationException("address.addressName cannot be blank");
		}
	}

	public void validateAddress(AddressRequestDTO addressRequestDTO) throws ValidationException {
		if (Objects.isNull(addressRequestDTO)) {
			throw new ValidationException("address is null");
		}
		if (Objects.isNull(addressRequestDTO.getAddressName()) || addressRequestDTO.getAddressName().isBlank()) {
			throw new ValidationException("address.addressName cannot be blank");
		}
	}

	public User addAddress(String userName, Address addressRequestDTO)
			throws ValidationException, UserNotFoundException {
		validateAddress(addressRequestDTO);
		Optional<User> foundUserOptional = this.userRepository.findByUserName(userName);
		if (foundUserOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		User foundUser = foundUserOptional.get();
		Address address = new Address();
		address.setAddressName(addressRequestDTO.getAddressName());
		address.setUsers(foundUser);
		foundUser.getAddress().add(address);
		return this.userRepository.save(foundUser);

	}

	public List<GetAddressByUserResponseDTO> findAddressByUser(String userName) throws ValidationException, UserNotFoundException {
		Optional<User> foundUserName = this.userRepository.findByUserName(userName);
		if (foundUserName.isEmpty()) {
			throw new UserNotFoundException();
		}
		User foundUser = foundUserName.get();
		List<Address> address = foundUser.getAddress();
		List<GetAddressByUserResponseDTO> addressDTOs = address.stream()
				.map(addressDTO -> new GetAddressByUserResponseDTO(addressDTO.getUsers().getUserName(),addressDTO.getAddressID(), addressDTO.getAddressName())).toList();
		return addressDTOs;
	}
	public Address updateAddress(String userName,Address newAddress) throws UserNotFoundException {
		Optional<User> foundUserOptional=this.userRepository.findByUserName(userName);
		if(foundUserOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		User user= foundUserOptional.get();
		Optional<Address> foundAddressOptional = this.addressRepository.findById(newAddress.getAddressID());
		if (foundAddressOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		Address updateAddress= foundAddressOptional.get();
		updateAddress.setAddressName(newAddress.getAddressName());
		this.userRepository.save(user);
		return this.addressRepository.save(updateAddress);
	}

	public boolean deleteAddress (Integer addressID,String userName) throws  UserNotFoundException, AddressNotFoundException {
		Optional<User> foundUserOptional = this.userRepository.findByUserName(userName);
		if (foundUserOptional.isEmpty()) {
			throw new UserNotFoundException();
		}
		User user= foundUserOptional.get();
		Optional<Address> foundAddressOptional=this.addressRepository.findById(addressID);
		if(foundAddressOptional.isEmpty()) {
			throw new AddressNotFoundException();
		}
		Address address=foundAddressOptional.get();
		user.getAddress().remove(address);
		this.addressRepository.delete(address);
		return true;
	}

}
