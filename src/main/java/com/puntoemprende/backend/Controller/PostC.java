/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Post;
import com.puntoemprende.backend.Model.User;
import com.puntoemprende.backend.Service.PostS;
import com.puntoemprende.backend.Service.UserS;
import jakarta.annotation.security.PermitAll;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import validations.onCreate;
import validations.onUpdate;

/**
 *
 * @author Ruisu's
 */
@RequestMapping("/api/post")
@CrossOrigin
@RestController
public class PostC {
    
    
    @Autowired
    private PostS postS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(postS.getById(id));
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('business') or hasRole('Administrador')")
    public ResponseEntity<Post> createPost(@Validated(onCreate.class) @RequestBody Post post){
        return ResponseEntity.status(HttpStatus.CREATED).body(postS.createPost(post));
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasRole('business') or hasRole('Administrador')")
    public ResponseEntity<Post> updatePost(@Validated(onUpdate.class) @RequestBody Post post){
        return ResponseEntity.status(HttpStatus.CREATED).body(postS.updatePost(post));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('business') or hasRole('Administrador')")
    public ResponseEntity<String> deletePost(@PathVariable("id") Integer id){
        postS.deletePost(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id+" eliminado");
    }
    
    @GetMapping("/search/{input}")
     @PreAuthorize("permitAll()")
    public ResponseEntity<List<Post>> findByCategory(@PathVariable("input") String input){
        return ResponseEntity.status(HttpStatus.OK).body(postS.findByTitleOrDescription(input));
    }
    
    @GetMapping("/find/category/{id}")
     @PreAuthorize("permitAll()")
    public ResponseEntity<List<Post>> findByCategory(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(postS.findByCategoryId(id));
    }
    
    @GetMapping("/find/scope/{id}")
     @PreAuthorize("permitAll()")
    public ResponseEntity<List<Post>> findByScope(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(postS.findByScopeId(id));
    }
    
    @GetMapping("/find/user/{id}")
     @PreAuthorize("permitAll()")
    public ResponseEntity<List<Post>> findByUser(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(postS.findByUserId(id));
    }
}
