/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.TypeDocument;
import com.puntoemprende.backend.Model.User;
import com.puntoemprende.backend.Service.TypeDocumentS;
import com.puntoemprende.backend.Service.UserS;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserC {
    
    @Autowired
    private UserS userS;
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(userS.getUsers());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getType(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userS.getUser(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<User> createType(@Validated(onCreate.class) @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userS.createUser(user));
    }
    
    @PutMapping("/update")
    public ResponseEntity<User> updateType(@Validated(onUpdate.class) @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userS.updateUser(user));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteType(@PathVariable("id") Integer id){
        userS.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id+" eliminado");
    }
    
}
