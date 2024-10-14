/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.CountryJR;
import com.puntoemprende.backend.Model.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class CountryR {
    
    @Autowired
    private CountryJR countryJR;
    
    public List<Country> getAll(){
        return countryJR.findAll();
    }
    
    public Optional<Country> getById(int id){
        return countryJR.findById(id);
    }
    
    public Country createCountry(Country country){
        return countryJR.save(country);
    }
    
    public Country updateCountry(Country country){
        return countryJR.save(country);
    }
    
    public void deleteCountry(int id){
        countryJR.deleteById(id);
    }
}
