package com.ibm.casestudy.accountms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicConfig extends WebSecurityConfigurerAdapter {
	
@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
return super.authenticationManagerBean();
}
//@formatter:off
@Bean
public UserDetailsService userDetasssilsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();
    return  new InMemoryUserDetailsManager(user);
}

@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {


	/*
	 * httpSecurity .formLogin() .loginPage("/login.html").permitAll()
	 * .failureUrl("/login-error.html").permitAll() .and() .logout().permitAll()
	 * .logoutSuccessUrl("/index.html").permitAll();
	 */
//httpSecurity.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();

//We don't need CSRF for this example
httpSecurity.csrf().disable()
//dont authenticate this particular request
.authorizeRequests().antMatchers("/login").permitAll()
.antMatchers("/resources/**").permitAll().anyRequest().permitAll().and()
// all other requests need to be authenticated
 //anyRequest().authenticated().and()
.formLogin().loginPage("/login.html").permitAll().usernameParameter("userName").passwordParameter("password").permitAll().loginProcessingUrl("/loginCustom").permitAll().successForwardUrl("/loginSuccess").permitAll().and()
//.loginPage("/login.html").loginProcessingUrl("/login").failureUrl("/login-error.html").permitAll().and().
 //for basic default login form
//.and().httpBasic().and()
// make sure we use stateless session; session won't be used to
// store user's state.
//exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().
  .sessionManagement()
.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// Add a filter to validate the tokens with every request
//httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
}
}
