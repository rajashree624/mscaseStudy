package com.ibm.casestudy.orderms.security;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.casestudy.orderms.model.Token;
import com.ibm.casestudy.orderms.repository.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class TokenValidator {
	
	@Value("${jwt.secret}")
    private String secret;
	
	@Value("${jwt.transaction-secret}")
    private String transactionSecret;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	   //validate token
		public Boolean validateToken(String token) {
		//final String username = getUsernameFromToken(token);	
		return (!isTokenExpired(token,secret));
		}
		
		// validate token
		public Boolean validateTrasactionToken(String tranToken) {
			final String username = getUsernameFromTransactionToken(tranToken);
			Optional<Token> optionalToken = tokenRepository.findById(username);
			boolean dbValid = false;
			boolean signatureValid = false;
			boolean isTokenValid = false;
			if(optionalToken.isPresent())
			{
				Token token = optionalToken.get();
				if(tranToken.equals(token.getTransactionToken()) && token.getIsValid())
				{
					dbValid = true;
					invalidateTransactionToken(token);
				}
			}
			signatureValid = !isTokenExpired(tranToken,transactionSecret);
			if(dbValid == true && signatureValid == true)
			{
				isTokenValid = true;
				
			}
			
			return isTokenValid;
		}
		
		private void invalidateTransactionToken(Token token) {
			// TODO Auto-generated method stub
			token.setIsValid(false);
			tokenRepository.save(token);
		}

		//retrieve username from jwt token
		public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject,secret);
		}
		
		//retrieve username from transaction token
		public String getUsernameFromTransactionToken(String token) {
			return getClaimFromToken(token, Claims::getSubject, transactionSecret);
		}
		
		public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver,String key) {
			final Claims claims = getAllClaimsFromToken(token,key);
			return claimsResolver.apply(claims);
			}
		
	    //for retrieveing any information from token we will need the secret key
		private Claims getAllClaimsFromToken(String token,String key) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		}
		
		//check if the token has expired
		private Boolean isTokenExpired(String token,String key) {
		final Date expiration = getExpirationDateFromToken(token,key);
		return expiration.before(new Date());
		}
		
		//retrieve expiration date from jwt token
		public Date getExpirationDateFromToken(String token, String key) {
		return getClaimFromToken(token, Claims::getExpiration,key);
		}

}
