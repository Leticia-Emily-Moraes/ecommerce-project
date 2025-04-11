package com.leticiamoraes.ecommerce.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private	String	name;

	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	private Integer stock_quantity;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private	Long category_id;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	protected Product() {
	}

	public Product(String pName, BigDecimal pPrice, Long pCategory_id) {
		this.name = pName;
		this.price = pPrice;
		this.category_id = pCategory_id;
		this.created_at = LocalDateTime.now();
		this.updated_at = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal pPrice) {
		this.price = pPrice;
	}

	public Integer getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(Integer pStock_quantity) {
		this.stock_quantity = pStock_quantity;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long pCategory_id) {
		this.category_id = pCategory_id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime pCreated_at) {
		this.created_at = pCreated_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime pUpdated_at) {
		this.updated_at = pUpdated_at;
	}
}
