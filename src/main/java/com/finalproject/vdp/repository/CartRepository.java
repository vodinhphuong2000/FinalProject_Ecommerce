package com.finalproject.vdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.vdp.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
