package com.leticiamoraes.ecommerce.service;

import com.leticiamoraes.ecommerce.model.*;

import java.util.*;

public interface ProductService {
	Product save(Product pProduct);

	Optional<Product> findById(Long pId);

	Optional<Product> findByName(String pName);

	List<Product> findByCategoryName(String pCategoryName);

	List<Product> findAll();

	Product update(Long pId, Product pProduct);

	Product delete(Long pId);
}

