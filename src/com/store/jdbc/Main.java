package com.store.jdbc;

import java.sql.*;

import dao.ProductDAO;

public class Main {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connection();
        
        Product product1 = new Product("Logitech keyboard", "A compact logitech keyboard.");
		Product product2 = new Product("Samsung A20", "An entry smartphone.");

		ProductDAO productDAO = new ProductDAO(connection);
		connection.setAutoCommit(false);

		try (PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO products (name, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);) {
			productDAO.add(product1);
			productDAO.add(product2);

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
        

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM products");

        statement.execute();

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");

            System.out.println(id);
            System.out.println(name);
            System.out.println(description);
        }

        statement.close();
        connection.close();
	}

}
