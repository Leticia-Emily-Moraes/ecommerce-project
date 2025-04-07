package com.leticiamoraes.ecommerce.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private	String	name;

	private String description;

	private BigDecimal price;

	private Integer stock_quantity;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private	Long category_id;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	public Product() {
	}

}
