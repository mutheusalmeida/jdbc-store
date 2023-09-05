package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Product;

public class CategoryDAO {
	private Connection connection;
	
	public CategoryDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Category> list () throws SQLException {
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
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categories;
	}
}
