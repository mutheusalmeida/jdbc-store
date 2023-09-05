package com.store.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.*;

public class ConnectionFactory {
	public DataSource dataSource;

    public ConnectionFactory () {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/store?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("1244");
        
        comboPooledDataSource.setMaxPoolSize(15);
        
        this.dataSource = comboPooledDataSource;
    }

    public Connection connection () throws SQLException {
        return this.dataSource.getConnection();
    }
}
