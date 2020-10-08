package com.ibm.casestudy.orderms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="token_details")
public class Token {

	@Id
	private String userName;
	private String transactionToken;
	private Boolean isValid;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTransactionToken() {
		return transactionToken;
	}
	public void setTransactionToken(String transactionToken) {
		this.transactionToken = transactionToken;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
	
	
	
}
