package com.store.jdbc;

import java.sql.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connection();

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
