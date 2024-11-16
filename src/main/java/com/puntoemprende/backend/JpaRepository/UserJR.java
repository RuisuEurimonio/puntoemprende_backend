/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.JpaRepository;

import com.puntoemprende.backend.Model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruisu's
 */
public interface UserJR extends JpaRepository<User, Integer>{
 
    public Optional<User> findByEmail(String email);
    
    public List<User> findByBusinessIsNotNull();
    
    public List<User> findByBusiness(String name);
}
