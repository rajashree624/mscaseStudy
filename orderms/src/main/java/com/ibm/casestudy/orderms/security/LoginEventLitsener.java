package com.ibm.casestudy.orderms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ibm.casestudy.orderms.model.Token;
import com.ibm.casestudy.orderms.repository.TokenRepository;


@Component
public class LoginEventLitsener {
	
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	TokenValidator tokenValidator;
	
	@StreamListener(LoginStream.INPUT)
	public void handleLogin(@Payload String transactionToken) {
           System.out.println("Token received "+transactionToken);
           transactionToken = transactionToken.toString().substring(1,transactionToken.toString().length()-1);
           //store in db
           Token token = new Token();
           String userName = tokenValidator.getUsernameFromTransactionToken(transactionToken);
           token.setUserName(userName);
           token.setTransactionToken(transactionToken);
           token.setIsValid(true);
           tokenRepository.save(token);
	}

}
