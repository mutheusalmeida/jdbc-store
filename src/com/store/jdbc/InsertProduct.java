package com.store.jdbc;

import java.sql.*;

public class InsertProduct {

	public static void main(String[] args) throws SQLException{
		ConnectionFactory connectionFactory = new ConnectionFactory();

        try (
                Connection connection = connectionFactory.connection();
                ) {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                    ) {
                insert(statement,"Logitech keyboard", "A compact logitech keyboard.");
                insert(statement,"Samsung A20", "An entry smartphone.");

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        }
	}

	public static void insert (PreparedStatement statement, String name, String description) throws SQLException {
		statement.setString(1, name);
        statement.setString(2, description);
        statement.execute();

        try (
        		ResultSet resultSet = statement.getGeneratedKeys();
                ) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);

                System.out.println("Product successfully added with ID: " + id);
            }
        }
	}
}
