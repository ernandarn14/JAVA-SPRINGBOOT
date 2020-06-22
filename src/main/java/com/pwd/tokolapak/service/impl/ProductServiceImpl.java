package com.pwd.tokolapak.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwd.tokolapak.dao.ProductRepo;
import com.pwd.tokolapak.entity.Product;
import com.pwd.tokolapak.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	@Transactional //undo perubahan ketika query gagal
	public Iterable<Product> getProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	@Transactional
	public Optional<Product> getProductsId(int id) {
		return productRepo.findById(id);
	}

	@Override
	@Transactional
	public Product addProduct(Product product) {
		product.setId(0);
		return productRepo.save(product);
	}

	@Override
	@Transactional
	public Product updateProduct(Product product) {
		Optional<Product> findProduct = productRepo.findById(product.getId());
		
		if (findProduct.get() == null)
			throw new RuntimeException("Product Not Found");
		
		return productRepo.save(product);
	}

	@Override
	@Transactional
	public void deleteProductById(int id) {
		// TODO Auto-generated method stub
		Optional<Product> findProduct = productRepo.findById(id);
		if(findProduct.toString() == "Optional.empty")
			throw new RuntimeException("Product with id " + id + " doesn't exist");
		productRepo.deleteById(id);
		
	}

}
