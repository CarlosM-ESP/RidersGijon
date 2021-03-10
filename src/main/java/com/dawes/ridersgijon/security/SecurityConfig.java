/**
 * 
 */
package com.dawes.ridersgijon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dawes.ridersgijon.serviceImpl.UserServiceImpl;

/**
 * @author CarlosM
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public String encode(String password) {
		return passwordEncoder().encode(password);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());		
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/pedidos/**")
			.authenticated();
		http.authorizeRequests()
        	.antMatchers("/admin/**")
        	.hasRole("ADMIN");
    	http.authorizeRequests()
        	.antMatchers("/clientes/**")
        	.hasAnyRole("CLIENT", "ADMIN");
    	http.authorizeRequests()
        	.antMatchers("/riders/**")
        	.hasAnyRole("RIDER","ADMIN");
    	http.formLogin().loginPage("/login").usernameParameter("email").defaultSuccessUrl("/loginUser");
    	http.exceptionHandling().accessDeniedPage("/403");
    	http.logout().logoutSuccessUrl("/index");
    	http.csrf().disable();
    	

    }
}
