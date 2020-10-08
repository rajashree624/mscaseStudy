package com.ibm.casestudy.orderms.security;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicConfiguration extends WebSecurityConfigurerAdapter {
	

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { PasswordEncoder encoder =
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 * auth.inMemoryAuthentication().withUser("user").password(encoder.encode(
	 * "password")).roles("USER").and()
	 * .withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
	 * }
	 */
	
	 private static final RequestMatcher SECURITY_EXCLUSION_MATCHER;
	    static {
	        String[] urls = new String[] {
	                "/receiveOrder"
	        };
	        //Build Matcher List
	        LinkedList<RequestMatcher> matcherList = new LinkedList<>();
	        for (String url : urls) {
	            matcherList.add(new AntPathRequestMatcher(url));
	        }

	        //Link Matchers in "OR" config.
	        SECURITY_EXCLUSION_MATCHER = new OrRequestMatcher(matcherList);
	    }

	
	@Autowired
	private RequestAuthenticationFilter requestAuthenticationFilter;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	   web.ignoring().antMatchers("/*");
		// web.ignoring().requestMatchers(SECURITY_EXCLUSION_MATCHER);
	    
		//web.
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		//this for displaying h2 console of you do not add this you will get an error - Refused to display 'http://localhost:8084/h2/tables.do?jsessionid=6856aaf9b9eee260102added6eb4a2dc' in a frame because it set 'X-Frame-Options' to 'deny'.
		http.headers().frameOptions().disable();
		//----------------------------------------------------------------------------------
		http
        .csrf().disable()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        .authorizeRequests()
            //.antMatchers("/receiveToken").permitAll()
        .antMatchers("/**").permitAll()
         .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
         ;
		//
		/*
		 * http.authorizeRequests()
		 * .requestMatchers(SECURITY_EXCLUSION_MATCHER).permitAll();
		 */

           // .anyRequest().authenticated();
		http.addFilterBefore(requestAuthenticationFilter, BasicAuthenticationFilter.class);
	}
	
	
}
