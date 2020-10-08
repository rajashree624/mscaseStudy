package com.ibm.casestudy.orderms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ibm.casestudy.orderms.model.Order;
import com.ibm.casestudy.orderms.model.OrderRequest;
import com.ibm.casestudy.orderms.repository.OrderRepository;



@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	public void addOrderDetails(OrderRequest request) {
		Order order = new Order();
		order.setUserName(request.getUserName());
		order.setOrderDetails(request.getOrderDetails());
		orderRepository.save(order);
		
	}
	public Optional<Order>  getOrderDetails(OrderRequest request) {
		
		Optional<Order> order = orderRepository.findById(request.getUserName());
		
		return order;
		
	}
	
	public Order  getOrderDetails2(OrderRequest request) {
		
		Order order = orderRepository.getOne(request.getUserName());
		
		return order;
		
	}
	
	

}
