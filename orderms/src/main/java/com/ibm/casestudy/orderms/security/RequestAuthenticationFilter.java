package com.ibm.casestudy.orderms.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class RequestAuthenticationFilter
extends OncePerRequestFilter 
//extends GenericFilterBean
implements MessageSourceAware{
	
	@Autowired
	TokenValidator tokenValidator;
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	//workaround for web.ignoring or security none equivalent not wrking in common conf
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if(request.getServletPath().endsWith("/h2") || request.getServletPath().endsWith("/placeOrder") 
				|| request.getServletPath().endsWith(".css") || request.getServletPath().endsWith(".js")
				|| request.getServletPath().endsWith(".ico"))
		{
			return true;
		}
		return false;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String token = request.getHeader("Authentication");
		String userName = tokenValidator.getUsernameFromToken(token);
		request.setAttribute("userName", userName); // response.set
		boolean isTokenValid = tokenValidator.validateToken(token);
		if (isTokenValid == false) {
			throw new AccessDeniedException(
					messages.getMessage("AbstractAccessDecisionManager.accessDenied", "Access Denied"));
		}
		final String transactionToken = request.getHeader("Transaction");
		String tranUserName = tokenValidator.getUsernameFromTransactionToken(transactionToken);
		if(userName.equals(tranUserName))
		{
			boolean isTransactionTokenValid = tokenValidator.validateTrasactionToken(transactionToken);
			isTransactionTokenValid = true;
			if(isTransactionTokenValid == false)
			{
				throw new AccessDeniedException(
						messages.getMessage("AbstractAccessDecisionManager.accessDenied", "Access Denied"));
			}
			
		}
		
		
		filterChain.doFilter(request, response);
	}
	 

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

	/*
	 * @Override public void doFilter(ServletRequest req, ServletResponse res,
	 * FilterChain filterChain) throws IOException, ServletException { // TODO
	 * Auto-generated method stub HttpServletRequest request = (HttpServletRequest)
	 * req; HttpServletResponse response = (HttpServletResponse) res; final String
	 * token = request.getHeader("Authentication"); String userName =
	 * tokenValidator.getUsernameFromToken(token); request.setAttribute("userName",
	 * userName); //response.set boolean isTokenValid =
	 * tokenValidator.validateToken(token); if(isTokenValid == false) { throw new
	 * AccessDeniedException(messages.getMessage(
	 * "AbstractAccessDecisionManager.accessDenied", "Access Denied")); }
	 * filterChain.doFilter(request, response); }
	 */

}
