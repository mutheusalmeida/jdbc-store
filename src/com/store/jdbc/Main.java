package com.store.jdbc;

import java.sql.*;
import java.util.List;

import dao.CategoryDAO;
import model.Category;
import model.Product;

public class Main {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		try (
				Connection connection = connectionFactory.connection();
				) {
			CategoryDAO categoryDAO = new CategoryDAO(connection);
			
			List<Category> categories = categoryDAO.list();
			
			categories.stream().forEach(item -> {
				for (Product product : item.getProducts()) {
					System.out.println(item.getName() + ": " + product.getName());
				}
			});
		}
	}
}
