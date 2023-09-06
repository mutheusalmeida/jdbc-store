package com.store.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import com.store.jdbc.dao.CategoryDAO;
import com.store.jdbc.factory.ConnectionFactory;
import com.store.jdbc.model.Category;

public class CategoryController {
	CategoryDAO categoryDAO;
	
	public CategoryController() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.connection();
		
		this.categoryDAO = new CategoryDAO(connection);
	}

	public List<Category> getCategories() {
		List<Category> categories = categoryDAO.getCategories();

		return categories;
	}
}
