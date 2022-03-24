package com.capg.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capg.entity.User;
import com.capg.service.CustomerService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	  @Autowired
	  CustomerService customerService;

	  @Override
      protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		  List<User> userList = customerService.readUsers();
          for (User users : userList) {
          auth.inMemoryAuthentication()
	                        .withUser(users.getUsername())
		                    .password(passwordEncoder().encode(users.getPassword()))
		                    .roles(users.getRole());
		        }
		    }
	
	
	
//    @Override 
//	protected  void configure(AuthenticationManagerBuilder auth)throws Exception{
//    	auth
//     	.inMemoryAuthentication()
//     	.withUser("ankit")
//     	.password(passwordEncoder().encode("180202"))
//     	.roles("CUSTOMER")
//    	.and()
//    	.withUser("admin")
//     	.password(passwordEncoder().encode("admin123"))
//     	.roles("ADMIN");
// 
//	}

	@Override
    protected void configure(HttpSecurity http)throws Exception{
    	http.authorizeRequests()
    	            .antMatchers("/customers/create")
    	            .hasRole("ADMIN")
    	            .anyRequest()
    	            .authenticated()
    	            .and()
    	            .httpBasic();
    	http.csrf().disable();
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
   
     
}