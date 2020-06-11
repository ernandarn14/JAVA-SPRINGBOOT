package com.pwd.tokolapak.service;

import java.util.Optional;
import com.pwd.tokolapak.entity.Product;

public interface ProductService {
	public Iterable<Product> getProducts();
	public Optional<Product> getProductsId(int id);
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public void deleteProductById(int id);
}
