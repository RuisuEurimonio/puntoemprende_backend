/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.TypeDocument;
import com.puntoemprende.backend.Service.TypeDocumentS;
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
@RequestMapping("/api/typedocument")
@CrossOrigin
public class TypeDocumentC {
    
    @Autowired
    private TypeDocumentS typeDocumentS;
    
    @GetMapping("/all")
    public ResponseEntity<List<TypeDocument>> getTypes(){
        return ResponseEntity.status(HttpStatus.OK).body(typeDocumentS.getTypes());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<TypeDocument>> getType(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(typeDocumentS.getType(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<TypeDocument> createType(@Validated(onCreate.class) @RequestBody TypeDocument type){
        return ResponseEntity.status(HttpStatus.CREATED).body(typeDocumentS.createType(type));
    }
    
    @PutMapping("/update")
    public ResponseEntity<TypeDocument> updateType(@Validated(onUpdate.class) @RequestBody TypeDocument type){
        return ResponseEntity.status(HttpStatus.CREATED).body(typeDocumentS.updateType(type));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteType(@PathVariable("id") Integer id){
        typeDocumentS.deleteType(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id+" eliminado");
    }
}
