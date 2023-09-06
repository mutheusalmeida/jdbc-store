package com.store.jdbc.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.store.jdbc.controller.CategoryController;
import com.store.jdbc.controller.ProductController;
import com.store.jdbc.model.Category;
import com.store.jdbc.model.Product;

public class ProductsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel labelName, labelDescription, labelCategory;
	private JTextField fieldName, fieldDescription;
	private JComboBox<Category> comboCategory;
	private JButton btnSave, btnClear, btnDelete;
	private JTable table;
	private DefaultTableModel model;
	private ProductController productController;
	private CategoryController categoryController;

	public ProductsFrame() {
		super("Products");
		Container container = getContentPane();
		setLayout(null);

		this.categoryController = new CategoryController();
		this.productController = new ProductController();

		labelName = new JLabel("Name");
		labelDescription = new JLabel("Description");
		labelCategory = new JLabel("Category");

		labelName.setBounds(10, 10, 240, 16);
		labelDescription.setBounds(10, 50, 240, 16);
		labelCategory.setBounds(10, 90, 240, 16);

		labelName.setForeground(Color.BLACK);
		labelDescription.setForeground(Color.BLACK);
		labelCategory.setForeground(Color.BLACK);

		container.add(labelName);
		container.add(labelDescription);
		container.add(labelCategory);

		fieldName = new JTextField();
		fieldDescription = new JTextField();
		comboCategory = new JComboBox<Category>();

		comboCategory.addItem(new Category(0, "Select"));
		List<Category> categories = this.getCategories();
		
		for (Category category : categories) {
			comboCategory.addItem(category);
		}

		fieldName.setBounds(10, 25, 265, 20);
		fieldDescription.setBounds(10, 65, 265, 20);
		comboCategory.setBounds(10, 105, 265, 20);

		container.add(fieldName);
		container.add(fieldDescription);
		container.add(comboCategory);

		btnSave = new JButton("Save");
		btnClear = new JButton("Clear");

		btnSave.setBounds(10, 145, 80, 20);
		btnClear.setBounds(100, 145, 80, 20);

		container.add(btnSave);
		container.add(btnClear);

		table = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	            return column != 0;
			}
		};
		
		model = (DefaultTableModel) table.getModel();

		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Description");

		this.fillTable();

		table.setBounds(10, 185, 760, 300);
		
		container.add(table);

		btnDelete = new JButton("Delete");

		btnDelete.setBounds(10, 500, 80, 20);

		container.add(btnDelete);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
				clearTable();
				fillTable();
			}
		});

		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
				clearTable();
				fillTable();
			}
		});
		
		model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == 0) {					
					edit();
				}
			}
		});
	}
	
	private void clearTable() {
		model.getDataVector().clear();
	}

	private void edit() {
		Integer id = (Integer) model.getValueAt(table.getSelectedRow(), 0);
		String name = (String) model.getValueAt(table.getSelectedRow(), 1);
		String description = (String) model.getValueAt(table.getSelectedRow(), 2);
		
		this.productController.edit(name, description, id);
		
		JOptionPane.showMessageDialog(rootPane, "Product successfully updated!");
	}

	private void delete() {
		Integer id = (Integer) model.getValueAt(table.getSelectedRow(), 0);
		this.productController.delete(id);
		model.removeRow(table.getSelectedRow());

		JOptionPane.showMessageDialog(this, "Product successfully deleted!");
	}

	private void fillTable() {
		List<Product> products = this.getProducts();
		
		try {
			for (Product product : products) {
				model.addRow(new Object[] { product.getId(), product.getName(), product.getDescription() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private List<Category> getCategories() {
		return this.categoryController.getCategories();
	}

	private void add() {
		if (!fieldName.getText().equals("") && !fieldDescription.getText().equals("")) {
			Product product = new Product(fieldName.getText(), fieldDescription.getText());
			Category category = (Category) comboCategory.getSelectedItem();
			product.setCategoryId(category.getId());
			this.productController.add(product);
			
			JOptionPane.showMessageDialog(this, "Product successfully added!");
			this.clear();
		} else {
			JOptionPane.showMessageDialog(this, "Name and description are required.");
		}
	}

	private List<Product> getProducts() {
		return this.productController.getProducts();
	}

	private void clear() {
		this.fieldName.setText("");
		this.fieldDescription.setText("");
		this.comboCategory.setSelectedIndex(0);
	}
}
