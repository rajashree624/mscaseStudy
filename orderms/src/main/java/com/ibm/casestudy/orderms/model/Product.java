package com.ibm.casestudy.orderms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Product")
public class Product {
	
	@Id
	private int productID;
	private int quantity;
	private String description;
	private double price;
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
