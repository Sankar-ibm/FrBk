/*
 * (c) 2021 IBM Financial Industry Solutions GmbH, All rights reserved.
 */

package com.ibm.nettrader.brokerage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  /**
   * Configure.
   *
   * @param auth the auth
   * @throws Exception the exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
                .withUser("consors").password("{noop}consors#$987").roles("USER")
                .and()
                .withUser("admin").password("{noop}consors#$987").roles("ADMIN");
  }
  /**
   * User details service.
   *
   * @return the user details service
   */
  
  @Bean
  public UserDetailsService userDetailsService() {
    User.UserBuilder users = User.withDefaultPasswordEncoder();
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(users.username("consors").password("consors#$987").roles("USER").build());
    manager.createUser(users.username("admin").password("consors#$987").roles("USER", "ADMIN")
         .build());
    return manager;
  }
}
