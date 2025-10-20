package com.leticiamoraes.ecommerce.controller;

import com.leticiamoraes.ecommerce.model.Category;
import com.leticiamoraes.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	private final CategoryService categoryService;

	@Autowired
	public  CategoryController(CategoryService pCategoryService){
		this.categoryService = pCategoryService;
	}

	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> categories = categoryService.findAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping
	public ResponseEntity<Category> findById(@RequestParam("id") Long pId){
		return categoryService.findById(pId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<Category> findByName(@RequestParam("name") String pName){
		Optional<Category> category = categoryService.findByName(pName);

		return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category pCategory){
		Category saved = categoryService.save(pCategory);
		return ResponseEntity.ok(saved);
	}

	@PatchMapping
	public ResponseEntity<Category> update(@RequestParam("id") Long pId, @RequestBody Category pCategory){
		Category updated = categoryService.update(pId, pCategory);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping
	public ResponseEntity<Category> delete(@RequestParam("id") Long pId){
		Category deleted = categoryService.delete(pId);
		return ResponseEntity.ok(deleted);
	}

}
