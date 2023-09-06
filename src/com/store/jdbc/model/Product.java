package com.store.jdbc.model;

public class Product {
	private Integer id;
	private String name;
	private String description;
	private Integer categoryId;

	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Product(Integer id, String name, String description) {
		this(name, description);
		this.id = id;
	}
	
	public void setId(Integer id) {
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
		return String.format("Product: %d, %s, %s", this.id, this.name, this.description);	
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Integer getCategoryId() {
		return this.categoryId;
	}
}
