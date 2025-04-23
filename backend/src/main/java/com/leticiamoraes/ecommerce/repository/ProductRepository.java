package com.leticiamoraes.ecommerce.repository;

import com.leticiamoraes.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByName(String pName);

	List<Product> findByCategoryName(String categoryName);
}
