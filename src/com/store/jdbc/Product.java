package com.store.jdbc;

public class Product {
	private int id;
	private String name;
	private String description;

	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "Product: " + this.id + ", " + this.name + ", " + this.description;	
	}
}
