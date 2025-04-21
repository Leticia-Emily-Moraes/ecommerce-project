package com.leticiamoraes.ecommerce.service;

import com.leticiamoraes.ecommerce.model.Category;

import java.util.*;

public interface CategoryService {
	Category save(Category pCategory);

	Optional<Category> findById(Long pId);

	Optional<Category> findByName(String pName);

	List<Category> findAll();

	Category update(Long pId, Category pCategory);

	Category delete(Long pId);
}
