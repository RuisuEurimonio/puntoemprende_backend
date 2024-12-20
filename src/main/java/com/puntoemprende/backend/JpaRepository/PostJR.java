/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.JpaRepository;

import com.puntoemprende.backend.Model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruisu's
 */
public interface PostJR extends JpaRepository<Post, Integer>{
    
    public List<Post> findByTitleContainingOrDescriptionContaining(String input, String input2);
 
    public List<Post> findByScopeId(int id);
    
    public List<Post> findByCategoryId(int id);
    
    public List<Post> findByUserId(int id);
}