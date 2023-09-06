package com.store.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import com.store.jdbc.dao.ProductDAO;
import com.store.jdbc.factory.ConnectionFactory;
import com.store.jdbc.model.Product;

public class ProductController {
	ProductDAO productDAO;
	
	public ProductController() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connection();
		
		this.productDAO = new ProductDAO(connection);
	}
	
	public void delete (Integer id) {
		this.productDAO.delete(id);
	}
	
	public void add (Product product) {
		this.productDAO.add(product);
	}
	
	public void edit (String name, String description, Integer id) {
		this.productDAO.edit(name, description, id);
	}
	
	public List<Product> getProducts() {
		List<Product> products = productDAO.getProducts();

		return products;
	}
}
