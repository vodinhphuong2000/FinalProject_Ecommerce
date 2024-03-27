package com.finalproject.vdp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_role_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer role_userID;
}
