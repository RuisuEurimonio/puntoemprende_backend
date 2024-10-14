/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Country;
import com.puntoemprende.backend.Repository.CountryR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class CountryS {
    
    @Autowired
    private CountryR countryR;
    
    public List<Country> getAll(){
        return countryR.getAll();
    }
    
    public Country getById(Integer id){
        Country country = countryR.getById(id).orElseThrow(() -> new CustomException("No se encontró el país."));
        return country;
    }
    
    public Country saveCountry(Country country){
        return countryR.createCountry(country);
    }
    
    public Country updateCountry(Country country){
        Country countryDB = countryR.getById(country.getId()).orElseThrow(()-> new CustomException("No se encontró el país a actualizar"));
        if(country.getName() != null){
            countryDB.setName(country.getName());
        }
        if(country.getPrefix() != null){
            countryDB.setPrefix(country.getPrefix());
        }
        return countryR.createCountry(countryDB);
    }
    
    public void deleteCountry(Integer id){
        countryR.getById(id).orElseThrow(()-> new CustomException("No se encontró el país a eliminar"));
        countryR.deleteCountry(id);
    }
}
