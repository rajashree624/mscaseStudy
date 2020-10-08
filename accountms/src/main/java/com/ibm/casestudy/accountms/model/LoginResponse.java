package com.ibm.casestudy.accountms.model;

import java.io.Serializable;

public class LoginResponse implements Serializable{
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;
	private final String transactionToken;
	public LoginResponse(String jwtToken,String transactionToken) {
	this.jwtToken = jwtToken;
	this.transactionToken = transactionToken;
	}
	public String getToken() {
	return this.jwtToken;
	}
	public String getTransactionToken() {
		return transactionToken;
	}
	
}
