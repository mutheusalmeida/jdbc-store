package model;

public class Product {
	private Integer id;
	private String name;
	private String description;

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
}
