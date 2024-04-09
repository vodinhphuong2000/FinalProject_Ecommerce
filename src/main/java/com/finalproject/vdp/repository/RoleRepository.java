package com.finalproject.vdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.vdp.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
