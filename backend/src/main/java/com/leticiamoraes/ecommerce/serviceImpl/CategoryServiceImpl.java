package com.leticiamoraes.ecommerce.serviceImpl;

import com.leticiamoraes.ecommerce.model.Category;
import com.leticiamoraes.ecommerce.repository.CategoryRepository;
import com.leticiamoraes.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository pCategoryRepository) {
		this.categoryRepository = pCategoryRepository;
	}

	@Override
	public Category save(Category pCategory) {
		if (pCategory.getName() == null || pCategory.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}

		if (categoryRepository.findByName(pCategory.getName()).isPresent()) {
			throw new IllegalArgumentException("Name already exists");
		}

		return categoryRepository.save(pCategory);
	}

	@Override
	public Optional<Category> findById(Long pId) {
		return categoryRepository.findById(pId);
	}

	@Override
	public Optional<Category> findByName(String pName) {
		return categoryRepository.findByName(pName);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category update(Long pId, Category pCategory) {
		return categoryRepository.findById(pId).map(existingCategory -> {
			if (pCategory.getName() != null && !pCategory.getName().trim().isEmpty()
					&& !pCategory.getName().equals(existingCategory.getName())) {

				if (categoryRepository.findByName(pCategory.getName()).isPresent()) {
					throw new IllegalArgumentException("Category already exists");
				}
				existingCategory.setName(pCategory.getName());
			}
			if (pCategory.getDescription() != null && !pCategory.getDescription().trim().isEmpty()) {
				existingCategory.setDescription(pCategory.getDescription());
			}

			return categoryRepository.save(existingCategory);
		}).orElseThrow(() -> new RuntimeException("Category not found by id " + pId));
	}


	@Override
	public Category delete(Long pId) {
		Category category = categoryRepository.findById(pId).orElseThrow(() -> new IllegalArgumentException("Category not found"));

		categoryRepository.delete(category);
		return category;
	}
}
