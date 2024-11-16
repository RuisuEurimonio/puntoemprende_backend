/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.UserJR;
import com.puntoemprende.backend.Model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class UserR {
    
    @Autowired
    private UserJR userJR;
    
    public List<User> getAll(){
        return userJR.findAll();
    }
    
    public Optional<User> getById(int id){
        return userJR.findById(id);
    }
    
    public User createUser(User user){
        return userJR.save(user);
    }
    
    public User updateUser(User user){
        return userJR.save(user);
    }
    
    public void deleteUser(int id){
        userJR.deleteById(id);
    }
    
    public Optional<User> findByEmail(String email){
        return userJR.findByEmail(email);
    }
    
    public List<User> findByBusinessIsNotNull(){
        return userJR.findByBusinessIsNotNull();
    }
    
    public List<User> findByBusiness(String name){
        return userJR.findByBusiness(name);
    }
}
