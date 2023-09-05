package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
	Connection connection;
	
	public ProductDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void add (Product product) throws SQLException {
		String sql = "INSERT INTO products (name, description) VALUES (?, ?)";
		
		try (				
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {	
			connection.setAutoCommit(false);
			
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			
			statement.execute();
			connection.commit();
			
			try (
					ResultSet resultSet = statement.getGeneratedKeys();
					) {
				while (resultSet.next()) {
					Integer id = resultSet.getInt(1);
					product.setId(id);
					
					System.out.println("Product successfully added with ID: " + id);
				}
			}
		} catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }

	}
	
	public List<Product> list () throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM products";
	
		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {			
			statement.execute();
			
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
		} catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
		
		return products;
	}
}
