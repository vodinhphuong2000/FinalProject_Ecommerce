package com.finalproject.vdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.vdp.model.Oder;
@Repository
public interface OrderRepository extends JpaRepository<Oder, Integer> {

}
