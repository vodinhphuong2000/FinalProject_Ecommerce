package com.finalproject.vdp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.vdp.model.Category;
import com.finalproject.vdp.model.Product;
@Repository

public interface ProductReponsitory extends JpaRepository<Product, Integer> {

	Page<Product> findByCategory(Category categoryID, Pageable pageable);
	Optional<Product> findById(Integer productID);
	
}
