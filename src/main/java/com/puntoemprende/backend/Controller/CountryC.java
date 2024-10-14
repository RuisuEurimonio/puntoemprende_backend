/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Country;
import com.puntoemprende.backend.Service.CountryS;
import java.net.http.HttpResponse;
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
@RequestMapping("/api/country")
@CrossOrigin
public class CountryC {
    
    @Autowired
    private CountryS countryS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(countryS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Country> getById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(countryS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Country> createCountry(@Validated(onCreate.class) @RequestBody Country country){
        return ResponseEntity.status(HttpStatus.CREATED).body(countryS.saveCountry(country));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Country> updateCountry(@Validated(onUpdate.class) @RequestBody Country country){
        return ResponseEntity.status(HttpStatus.CREATED).body(countryS.updateCountry(country));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") Integer id){
        countryS.deleteCountry(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se elimino el país con id: "+id);
    }
    
}
