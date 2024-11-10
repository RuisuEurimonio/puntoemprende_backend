/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Security;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.User;
import com.puntoemprende.backend.Service.UserS;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ruisu's
 */
@Component
public class JwtFilter {
    
    @Autowired
    private UserS userS;
    
    @Autowired
    private JwtService jwtS;
    
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException{
        
        String authHeader = request.getHeader("Authorization");
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        
        String jwt = authHeader.split(" ")[1];
        String username = null;
        
        try{
            username = jwtS.getName(jwt);
        } catch(NoSuchAlgorithmException ex){
            Logger.getLogger(JwtFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        User user = userS.findByEmail(username).orElseThrow(()-> new CustomException("No se encontro el usuario en la validación JWT"));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
        
    }
}