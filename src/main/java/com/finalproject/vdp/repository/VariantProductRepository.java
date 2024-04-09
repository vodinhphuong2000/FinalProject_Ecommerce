package com.finalproject.vdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.vdp.model.VariantProduct;
@Repository
public interface VariantProductRepository extends JpaRepository<VariantProduct, Integer> {
//List<VariantProduct> findByVariant_productID(Integer variant_productID);
}
