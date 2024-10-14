/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Scope;
import com.puntoemprende.backend.Service.ScopeS;
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
@RestController
@RequestMapping("/api/scope")
@CrossOrigin
public class ScopeC {
    
    
    @Autowired
    private ScopeS scopeS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Scope>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(scopeS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Scope> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(scopeS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Scope> createMotive(@Validated(onCreate.class) @RequestBody Scope motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(scopeS.createScope(motive));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Scope> updateMotive(@Validated(onUpdate.class) @RequestBody Scope motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(scopeS.updateScope(motive));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMotive(@PathVariable("id") Integer id){
        scopeS.deleteScope(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Motivo eliminado con id; "+id);
    }
    
}
