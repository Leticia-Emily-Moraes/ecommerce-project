package com.leticiamoraes.ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

	@id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	public User() {
	}
}
