package com.pwd.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pwd.tokolapak.dao.ProductRepo;
import com.pwd.tokolapak.entity.Product;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		return productRepo.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getProductsId(@PathVariable() int id) {
		return productRepo.findById(id);
	}
}
