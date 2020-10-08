package com.ibm.casestudy.orderms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="order_detail")
public class Order {
	
	//@Id 
	//@GeneratedValue(strategy=GenerationType.IDENTITY) not working identifier issue
	//private long id;
	@Id
	private String userName;
	private String orderDetails;
	
	
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
