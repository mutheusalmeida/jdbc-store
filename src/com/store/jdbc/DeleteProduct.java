package com.store.jdbc;

import java.sql.*;

public class DeleteProduct {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id > ?");

        statement.setInt(1,  2);
        statement.execute();

        Integer count = statement.getUpdateCount();

        System.out.println("Modified rows: " + count);

        statement.close();
        connection.close();
	}

}
