/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Motive;
import com.puntoemprende.backend.Service.MotiveS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/api/motive")
public class MotiveC {
    
    @Autowired
    private MotiveS motiveS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Motive>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(motiveS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Motive> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(motiveS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Motive> createMotive(@Validated(onCreate.class) @RequestBody Motive motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(motiveS.createMotive(motive));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Motive> updateMotive(@Validated(onUpdate.class) @RequestBody Motive motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(motiveS.updateMotive(motive));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMotive(@PathVariable("id") Integer id){
        motiveS.deleteMotive(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Motivo eliminado con id; "+id);
    }
    
}
