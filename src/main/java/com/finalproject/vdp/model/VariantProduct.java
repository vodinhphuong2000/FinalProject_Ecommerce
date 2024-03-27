package com.finalproject.vdp.model;

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
@Table(name = "table_variant_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariantProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer variant_productID;
	
	@Column(name = "col_color")
	private String color;
	
	@Column(name = "col_size")
	private String size;
	
	@Column(name = "col_model")
	private String model;
	
	@Column(name = "col_price")
	private String price;
	
	@ManyToOne
	@JoinColumn(name="productID",referencedColumnName = "productID",nullable = false)
	private Product products;
}