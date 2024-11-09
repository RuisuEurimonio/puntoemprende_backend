/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Security;

import Exceptions.CustomException;
import com.puntoemprende.backend.Service.UserS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ruisu's
 */
@Component
public class SecurityBeansConfig {
    
    @Autowired
    private UserS userS;
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return userName -> userS.findByEmail(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    //@Bean
    //public AuthenticationProvider authenticationProvider(){
      //  DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//    }
    
}
