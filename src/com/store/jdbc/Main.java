package com.store.jdbc;

import java.sql.*;
import java.util.List;

import dao.ProductDAO;
import model.Product;

public class Main {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
        
        Product product1 = new Product("Logitech keyboard", "A compact logitech keyboard.");
		Product product2 = new Product("Samsung A20", "An entry smartphone.");

        
		try (
				Connection connection = connectionFactory.connection();
				) {			
			ProductDAO productDAO = new ProductDAO(connection);
			
			productDAO.add(product1);
			productDAO.add(product2);
			
			List<Product> product = productDAO.list();
			
			product.stream().forEach(item -> System.out.println(item));
		}
	}
}
