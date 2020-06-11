package com.pwd.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pwd.tokolapak.dao.ProductRepo;
import com.pwd.tokolapak.entity.Product;
import com.pwd.tokolapak.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		return productService.getProducts(); //agar berhubungan dengan productService secara langsung
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getProductsId(@PathVariable int id) {
		return productService.getProductsId(id);
	}
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProductById(@PathVariable int id) {
		productService.deleteProductById(id);
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	
	@GetMapping("/productName/{productName}")
	public Product getProductByProductName(@PathVariable() String productName) {
		return productRepo.findByProductName(productName);
	}
}
