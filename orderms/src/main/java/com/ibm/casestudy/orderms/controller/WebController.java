package com.ibm.casestudy.orderms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	// Login form with error  
    @RequestMapping("/placeOrder")  
    public String loginSuccess(HttpServletRequest request,HttpServletResponse response,Model model) {  
    	System.out.println("inside login success");
        return "placeOrder.html";  
    } 
	
	
	
	

}
