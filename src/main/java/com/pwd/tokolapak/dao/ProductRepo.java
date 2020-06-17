package com.pwd.tokolapak.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pwd.tokolapak.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {
	public Product findByProductName(String productName);
	
	//indexed parameter
	@Query(value = "SELECT * FROM Product WHERE price > ?1 and product_name =?2", nativeQuery = true)
	public Iterable<Product> findProductByMinPrice(double minPrice, String productName);
	
	//named parameter
	@Query(value = "SELECT * FROM Product WHERE price > :maxPrice and product_name like %:productName%", nativeQuery = true)
	public Iterable<Product> findProductByMaxPrice(@Param("maxPrice") double maxPrice, @Param("productName") String productName);
}
