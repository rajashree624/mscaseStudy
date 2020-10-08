package com.ibm.casestudy.orderms.security;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LoginStream {
	
	final String INPUT = "login-in";

	 
	@Input(INPUT)
	SubscribableChannel inboundLoginChannel();

}
