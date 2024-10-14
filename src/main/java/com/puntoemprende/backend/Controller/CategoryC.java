/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Category;
import com.puntoemprende.backend.Service.CategoryS;
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
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryC {
    
    @Autowired
    private CategoryS categoryS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Category> createMotive(@Validated(onCreate.class) @RequestBody Category motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryS.createMotive(motive));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Category> updateMotive(@Validated(onUpdate.class) @RequestBody Category motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryS.updateMotive(motive));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMotive(@PathVariable("id") Integer id){
        categoryS.deleteMotive(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Motivo eliminado con id; "+id);
    }
    
}
