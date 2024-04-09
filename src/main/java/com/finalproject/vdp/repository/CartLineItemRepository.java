package com.finalproject.vdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.vdp.model.CartLineItem;
@Repository
public interface CartLineItemRepository extends JpaRepository<CartLineItem, Integer> {
	List<CartLineItem> findByIsDeleted(boolean isDeleted);
}
