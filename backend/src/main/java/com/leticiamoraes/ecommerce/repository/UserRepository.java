package com.leticiamoraes.ecommerce.repository;

import com.leticiamoraes.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String pEmail);

	boolean existsByEmail(String pEmail);
}