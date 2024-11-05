/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Scope;
import com.puntoemprende.backend.Model.System;
import com.puntoemprende.backend.Service.SystemS;
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
@RequestMapping("/api/system")
@CrossOrigin
public class SystemC {
    
    @Autowired
    private SystemS systemS;
    
    @GetMapping("/all")
    public ResponseEntity<List<System>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(systemS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<System> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(systemS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<System> createMotive(@Validated(onCreate.class) @RequestBody System system){
        return ResponseEntity.status(HttpStatus.CREATED).body(systemS.createSystem(system));
    }
    
    @PutMapping("/update")
    public ResponseEntity<System> updateMotive(@Validated(onUpdate.class) @RequestBody System system){
        return ResponseEntity.status(HttpStatus.CREATED).body(systemS.updateSystem(system));
    }
    
}
