package com.ibm.casestudy.accountms.model;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface LoginStream {
	
final String OUTPUT = "login-out";
	
	@Output(OUTPUT)
	SubscribableChannel outboundLogin();

}
