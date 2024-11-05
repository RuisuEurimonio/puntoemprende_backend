/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Town;
import com.puntoemprende.backend.Service.TownS;
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
@RequestMapping("/api/town")
@CrossOrigin
public class TownC {
    
    
    @Autowired
    private TownS townS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Town>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(townS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Town> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(townS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Town> createTown(@Validated(onCreate.class) @RequestBody Town motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(townS.createTown(motive));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Town> updateTown(@Validated(onUpdate.class) @RequestBody Town motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(townS.updateTown(motive));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTown(@PathVariable("id") Integer id){
        townS.deleteTown(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Municipio eliminado con id; "+id);
    }
    
    
}
