package com.ibm.casestudy.accountms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.casestudy.accountms.model.LoginRequest;
import com.ibm.casestudy.accountms.model.LoginResponse;
import com.ibm.casestudy.accountms.model.Token;
import com.ibm.casestudy.accountms.security.JwtTokenUtil;
import com.ibm.casestudy.accountms.security.LoginEventPublisher;



@RestController
public class LoginController {
	
	//@Autowired
	//TokenService tokenService;
	
	@Autowired
	LoginEventPublisher loginEventPublisher;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	     //login method for Authentication
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public ResponseEntity<LoginResponse> addConversionFactor(@RequestBody LoginRequest request) throws Exception {
			//check Optional in java 8
//			if(request.getUserName() != null && request.getPassword() != null)
//			{
			String userName = request.getUserName();
			String password = request.getPassword();
			authenticate(request.getUserName(), request.getPassword());
		final UserDetails userDetails = userDetailsService
					.loadUserByUsername(request.getUserName());
					final Token token = jwtTokenUtil.generateToken(userDetails);
					
			//tokenService.sendTokenSync(token);
			String jwtToken = token.getAccessToken();
			String transactionToken = token.getTransactionToken();
			loginEventPublisher.publishLoginEvent(transactionToken);
			//return null;
			//return token;
			return ResponseEntity.ok(new LoginResponse(jwtToken,transactionToken));
		}
		
		private void authenticate(String username, String password) throws Exception {
			try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
			}
			}

}
