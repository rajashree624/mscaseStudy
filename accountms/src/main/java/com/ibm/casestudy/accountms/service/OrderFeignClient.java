package com.ibm.casestudy.accountms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.casestudy.accountms.model.TokenRequest;




//@FeignClient(url = "http://localhost:8084", name ="order")
public interface OrderFeignClient {
	/*
	 * 
	 * @RequestMapping(value = "/receiveToken", method = RequestMethod.POST) public
	 * String receiveToken(@RequestBody TokenRequest request);
	 */}
