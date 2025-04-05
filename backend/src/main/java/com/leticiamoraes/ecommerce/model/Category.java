package com.leticiamoraes.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	private String description;

	public Category() {
	}


}
