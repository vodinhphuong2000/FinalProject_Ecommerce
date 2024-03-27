package com.finalproject.vdp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;
	
	@Column(name = "col_userName")
	private String userName;
	
	@Column(name = "col_password")
	private String password;
	
	@Column(name = "col_fullName")
	private String fullName;
	
	@Column(name = "col_phone")
	private Integer phone;
	
	@Column(name = "col_birthDate")
	private Date birthDate;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="table_role_user",joinColumns = @JoinColumn(name="userID",referencedColumnName = "userID"),inverseJoinColumns = @JoinColumn(name="roleID",referencedColumnName = "roleID"))
	private List<Role> roles;
	
	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Address> address= new ArrayList<Address>();
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cartID",referencedColumnName = "cartID",nullable = false)
	private Cart carts;
}
