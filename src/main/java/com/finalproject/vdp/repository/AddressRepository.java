package com.finalproject.vdp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.vdp.model.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	Optional<Address> findByAddressName(String addressName);

//	Optional<Address> findByIdAndUserId(Integer addressID, Integer userID);
}
