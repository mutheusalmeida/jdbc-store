package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.store.jdbc.Product;

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
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			
			statement.execute();
			
			try (
					ResultSet resultSet = statement.getGeneratedKeys();
					) {
				while (resultSet.next()) {
					Integer id = resultSet.getInt(1);
					product.setId(id);
					
					System.out.println("Product successfully added with ID: " + id);
				}
			}
		}

	}
}
