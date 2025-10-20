package com.leticiamoraes.ecommerce.controller;

import com.leticiamoraes.ecommerce.model.Product;
import com.leticiamoraes.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/products")
public class ProductController {
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService pProductService) {
		this.productService = pProductService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productService.findAll();
		return ResponseEntity.ok(products);
	}

	@GetMapping
	public ResponseEntity<Product> findById(@RequestParam("id") Long pId) {
		return productService.findById(pId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<Product> findByName(@RequestParam("name") String pName) {
		Optional<Product> product = productService.findByName(pName);

		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<Product>> findByCategoryName(@RequestParam("categoryName") String pCategoryName) {
		List<Product> products = productService.findByCategoryName(pCategoryName);

		if (products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(products);
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product pProduct) {
		Product saved = productService.save(pProduct);
		return ResponseEntity.ok(saved);
	}

	@PatchMapping
	public ResponseEntity<Product> update(@RequestParam("id") Long pId, @RequestBody Product pProduct) {
		Product updated = productService.update(pId, pProduct);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping
	public ResponseEntity<Product> delete(@RequestParam("id") Long pId) {
		Product deleted = productService.delete(pId);
		return ResponseEntity.ok(deleted);
	}
}
