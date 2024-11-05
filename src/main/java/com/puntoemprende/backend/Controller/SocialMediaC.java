/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Scope;
import com.puntoemprende.backend.Model.SocialMedia;
import com.puntoemprende.backend.Service.ScopeS;
import com.puntoemprende.backend.Service.SocialMediaS;
import java.util.List;
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
@RequestMapping("/api/social-media")
@RestController
@CrossOrigin
public class SocialMediaC {
    
    @Autowired
    private SocialMediaS socialMediaS;
    
    @GetMapping("/all")
    public ResponseEntity<List<SocialMedia>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(socialMediaS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<SocialMedia> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(socialMediaS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<SocialMedia> createMotive(@Validated(onCreate.class) @RequestBody SocialMedia socialMedia){
        return ResponseEntity.status(HttpStatus.CREATED).body(socialMediaS.createScope(socialMedia));
    }
    
    @PutMapping("/update")
    public ResponseEntity<SocialMedia> updateMotive(@Validated(onUpdate.class) @RequestBody SocialMedia socialMedia){
        return ResponseEntity.status(HttpStatus.CREATED).body(socialMediaS.updateScope(socialMedia));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMotive(@PathVariable("id") Integer id){
        socialMediaS.deleteScope(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Red social eliminada con id; "+id);
    }
    
    
}
