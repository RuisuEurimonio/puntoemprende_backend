/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.PermissionJR;
import com.puntoemprende.backend.JpaRepository.PostJR;
import com.puntoemprende.backend.Model.Permission;
import com.puntoemprende.backend.Model.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class PostR {
    
    @Autowired
    private PostJR postJr;
    
    public List<Post> getAll(){
        return postJr.findAll();
    }
    
    public Optional<Post> getById(int id){
        return postJr.findById(id);
    }    
    
    public Post createPost (Post post){
        return postJr.save(post);
    }
    
    public Post updatePost(Post post){
        return postJr.save(post);
    }
    
    public void deletePost(int id){
        postJr.deleteById(id);
    }
    
    public List<Post> findByTitleOrDescription(String input){
        return postJr.findByTitleContainingOrDescriptionContaining(input, input);
    }
    
    public List<Post> findByCategoryID(int id){
        return postJr.findByCategoryId(id);
    }
    
    public List<Post> findByScopeID(int id){
        return postJr.findByScopeId(id);
    }
    
    public List<Post> findByUserID(int id){
        return postJr.findByUserId(id);
    }
}
