package com.store.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.store.jdbc.model.Product;

public class ProductDAO {
	private Connection connection;
	
	public ProductDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void add (Product product) {
		String sql = "INSERT INTO products (name, description, category_id) VALUES (?, ?, ?)";
		
		try (				
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setInt(3, product.getCategoryId());
			
			statement.execute();
			
			try (
					ResultSet resultSet = statement.getGeneratedKeys();
					) {
				while (resultSet.next()) {
					Integer id = resultSet.getInt(1);
					product.setId(id);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Product> getProducts () {
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM products";
	
		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {			
			statement.execute();
			
			resultSetToProduct(products, statement);
			
			return products;		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void resultSetToProduct(List<Product> products, PreparedStatement statement) throws SQLException {
		try (
				ResultSet resultSet = statement.getResultSet();
				) {			
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				
				Product product = new Product(id, name, description);
				
				products.add(product);
			}
			
		}
	}
	
	public void delete (Integer id) {
		String sql = "DELETE FROM products WHERE id = ?";
		
		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {			
			statement.setInt(1,  id);
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit (String name, String description, Integer id) {
		String sql = "UPDATE products p SET p.name = ?, p.description = ? WHERE id = ?";
		
		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {			
			statement.setString(1,  name);
			statement.setString(2,  description);
			statement.setInt(3,  id);
			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
