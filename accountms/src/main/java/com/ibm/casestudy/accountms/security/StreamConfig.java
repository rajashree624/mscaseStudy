package com.ibm.casestudy.accountms.security;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(LoginStream.class)
public class StreamConfig {
	

}
