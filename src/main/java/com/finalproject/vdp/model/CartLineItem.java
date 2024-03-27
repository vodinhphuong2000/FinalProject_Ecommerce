package com.finalproject.vdp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_cartline_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartLineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartline_itemID;
	
	@Column(name = "col_quantity")
	private Integer quantity;
	
	@Column(name = "col_total_price")
	private float total_price;
	
	@Column(name = "col_added_date")
	private Date added_date;
	
	@Column(name = "col_isDeleted")
	private boolean isDeleted	;
	
	@ManyToOne
	@JoinColumn(name="oderID",referencedColumnName = "oderID",nullable = false)
	private Oder oder;
}
