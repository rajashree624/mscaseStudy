package com.ibm.casestudy.orderms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.casestudy.orderms.model.Order;



public interface OrderRepository extends JpaRepository<Order, String>{

}
