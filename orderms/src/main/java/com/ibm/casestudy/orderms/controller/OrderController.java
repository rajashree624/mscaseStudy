package com.ibm.casestudy.orderms.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.casestudy.orderms.model.Order;
import com.ibm.casestudy.orderms.model.OrderRequest;
import com.ibm.casestudy.orderms.model.TokenRequest;
import com.ibm.casestudy.orderms.service.OrderService;



@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	 //receive Order details
	@RequestMapping(value = "/receiveOrder", method = RequestMethod.POST)
	public String receiveOrder(HttpServletRequest httpRequest,@RequestBody OrderRequest request) {
		String order = request.getOrderDetails();
		System.out.println(httpRequest.getAttribute("userName"));
		request.setUserName(httpRequest.getAttribute("userName").toString());
		orderService.addOrderDetails(request);
		return "Recived Order details successfully - "+order;
	}
	@RequestMapping(value = "/getOrderDetails", method = RequestMethod.POST)
	public String getOrderDetails(HttpServletRequest httpRequest,@RequestBody OrderRequest request) {
		
		System.out.println(httpRequest.getAttribute("userName"));
		request.setUserName(httpRequest.getAttribute("userName").toString());
		Order order = orderService.getOrderDetails2(request);
		
		return "Fetched Order details successfully - "+order.getOrderDetails();
	}
	
	   //receive Token details
	//should be accessible only from login ms not any other
		@RequestMapping(value = "/receiveToken", method = RequestMethod.POST)
		public String receiveToken(@RequestBody TokenRequest request) {
			String token = request.getToken();
			return "Recived Token details successfully - "+token;
		}

}
