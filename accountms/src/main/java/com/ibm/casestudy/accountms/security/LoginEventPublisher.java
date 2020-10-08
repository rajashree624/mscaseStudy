package com.ibm.casestudy.accountms.security;

import java.util.concurrent.Executors;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginEventPublisher {

	private final LoginStream loginStream;
	
	public LoginEventPublisher(LoginStream loginStream)
	{
		this.loginStream = loginStream;
	}
	
	public void publishLoginEvent(final String response)
	{
		
		Executors.newFixedThreadPool(1).execute(() -> {

			String responseJSON = "";
			try {
				//Thread.sleep(2000);
				ObjectMapper obj = new ObjectMapper();
				responseJSON = obj.writeValueAsString(response);
				MessageChannel messageChannel = loginStream.outboundLogin();
				messageChannel.send(MessageBuilder.withPayload(responseJSON)
						.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN_VALUE).build());

			} catch (Throwable e) {
			}

		});
	}


}
