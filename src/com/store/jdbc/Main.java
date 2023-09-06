package com.store.jdbc;

import java.sql.*;

import javax.swing.JFrame;

import com.store.jdbc.view.ProductsFrame;

public class Main {

	public static void main(String[] args) throws SQLException {
		ProductsFrame productsFrame = new ProductsFrame();
		productsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
