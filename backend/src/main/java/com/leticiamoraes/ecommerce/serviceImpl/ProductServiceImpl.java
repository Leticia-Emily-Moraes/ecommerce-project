package com.leticiamoraes.ecommerce.serviceImpl;

import com.leticiamoraes.ecommerce.model.*;
import com.leticiamoraes.ecommerce.repository.ProductRepository;
import com.leticiamoraes.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository pProductRepository) {
		this.productRepository = pProductRepository;
	}

	@Override
	public Product save(Product pProduct) {
		if(pProduct.getName() == null || pProduct.getName().trim().isEmpty()){
			throw new IllegalArgumentException("Name cannot be empty");
		}

		if (pProduct.getPrice() == null || pProduct.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Price must be greater than zero");
		}

		if(pProduct.getCategory() == null){
			throw new IllegalArgumentException("Category cannot be empty");
		}

		pProduct.setCreated_at(LocalDateTime.now());
		pProduct.setUpdated_at(LocalDateTime.now());

		return productRepository.save(pProduct);
	}

	@Override
	public Optional<Product> findById(Long pId) {
		return productRepository.findById(pId);
	}

	@Override
	public Optional<Product> findByName(String pName) {
		return productRepository.findByName(pName);
	}

	@Override
	public List<Product> findByCategoryName(String categoryName) {
		return productRepository.findByCategoryName(categoryName.trim());
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product update(Long pId, Product pProduct) {
		return productRepository.findById(pId).map(existingProduct -> {
			if (pProduct.getName() != null && !pProduct.getName().trim().isEmpty()) {
				existingProduct.setName(pProduct.getName());
			}

			if (pProduct.getDescription() != null && !pProduct.getDescription().trim().isEmpty()) {
				existingProduct.setDescription(pProduct.getDescription());
			}

			if (pProduct.getPrice() == null || pProduct.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
				existingProduct.setPrice(pProduct.getPrice());
			}

			if (pProduct.getStock_quantity() != null && pProduct.getStock_quantity() > 0) {
				existingProduct.setStock_quantity(pProduct.getStock_quantity());
			}

			if (pProduct.getCategory() != null) {
				existingProduct.setCategory(pProduct.getCategory());
			}

			return productRepository.save(existingProduct);
		}).orElseThrow(() -> new RuntimeException("Product not found by id " + pId));
	}

	@Override
	public Product delete(Long pId) {
		Product product = productRepository.findById(pId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

		productRepository.delete(product);
		return product;
	}
}
