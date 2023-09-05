package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private Integer id;
	private String name;
	private List<Product> products = new ArrayList<Product>();

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("Category: %d, %s", this.id, this.name);
	}
	
	public String getName() {
		return this.name;
	}

	public void add(Product product) {
		this.products.add(product);
	}

	public List<Product> getProducts() {
		return this.products;
	}
}
