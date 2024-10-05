/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Repository.TypeDocumentR;
import java.util.List;
import java.util.Optional;
import jdk.jfr.BooleanFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ruisu's
 */
@Controller
@RequestMapping("/api/TypeDocument")
@CrossOrigin
public class TypeDocument {
    
    @Autowired
    private TypeDocumentR typeDocumentR;
    
    @GetMapping("/all")
    public ResponseEntity<List<com.puntoemprende.backend.Model.TypeDocument>> getTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(typeDocumentR.getTypes());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<com.puntoemprende.backend.Model.TypeDocument>> getType(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(typeDocumentR.getType(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<com.puntoemprende.backend.Model.TypeDocument> createType(@RequestBody com.puntoemprende.backend.Model.TypeDocument type){
        return ResponseEntity.status(HttpStatus.CREATED).body(typeDocumentR.createType(type));
    }
    
    @PutMapping("/update")
    public ResponseEntity<com.puntoemprende.backend.Model.TypeDocument> updateType(@RequestBody com.puntoemprende.backend.Model.TypeDocument type){
        return ResponseEntity.status(HttpStatus.CREATED).body(typeDocumentR.updateType(type));
    }
    
    @DeleteMapping("/delete/id")
    public ResponseEntity<String> deleteType(@PathVariable("id") Integer id){
        typeDocumentR.deleteType(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id+" eliminado");
    }
}
