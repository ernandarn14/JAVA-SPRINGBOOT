package com.pwd.tokolapak.dao;

import org.springframework.data.repository.CrudRepository;

import com.pwd.tokolapak.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {
	public Product findByProductName(String productName);
}
