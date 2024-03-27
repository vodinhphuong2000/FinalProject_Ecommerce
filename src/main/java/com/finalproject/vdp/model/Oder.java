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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_oder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oderID;
	
	@Column(name = "col_address")
	private String address;
	
	@Column(name = "col_deliveryTime")
	private Date deliveryTime;
	
	@Column(name = "col_totalPrice")
	private float totalPrice;
	
	@OneToMany(mappedBy = "oder",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<CartLineItem> cartLineItems =new ArrayList<CartLineItem>();
}
