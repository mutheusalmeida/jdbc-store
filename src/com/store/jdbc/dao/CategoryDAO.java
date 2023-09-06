package com.store.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.store.jdbc.model.Category;
import com.store.jdbc.model.Product;

public class CategoryDAO {
	private Connection connection;
	
	public CategoryDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Category> getCategories () {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM categories";
		
		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {
			statement.execute();
			
			try (
					ResultSet resultSet = statement.getResultSet()
					) {
				while (resultSet.next()) {					
						Integer id = resultSet.getInt(1);
						String name = resultSet.getString(2);
						
						Category category = new Category(id, name);
						
						categories.add(category);
					
				}
				
				return categories;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> getInnerJoinCategories () {
		Category lastItem = null;
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM categories c INNER JOIN products p ON c.id = p.category_id";
		
		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {
			statement.execute();
			
			try (
					ResultSet resultSet = statement.getResultSet()
					) {
				while (resultSet.next()) {
					if (lastItem == null || !lastItem.getName().equals(resultSet.getString(2))) {						
						Integer id = resultSet.getInt(1);
						String name = resultSet.getString(2);
						
						Category category = new Category(id, name);
						
						categories.add(category);
						lastItem = category;
					}
					
					Integer id = resultSet.getInt(3);
					String name = resultSet.getString(4);
					String description = resultSet.getString(5);
					
					Product product = new Product(id, name, description);
					
					lastItem.add(product);
				}
				
				return categories;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
