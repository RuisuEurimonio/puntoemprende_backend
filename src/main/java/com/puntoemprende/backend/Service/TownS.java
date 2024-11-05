/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Country;
import com.puntoemprende.backend.Model.Town;
import com.puntoemprende.backend.Repository.CountryR;
import com.puntoemprende.backend.Repository.TownR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service      
public class TownS {
    
    
    @Autowired
    private TownR townR;
    
    @Autowired
    private CountryR countryR;
    
    public List<Town> getAll(){
        return townR.getAll();
    }
    
    public Town getById(Integer id){
        Town category = townR.getById(id).orElseThrow(()-> new CustomException("No se encontró el municipio"));
        return category;
    }
    
    public Town createTown(Town town){
        return townR.createTown(town);
    }
    
    public Town updateTown(Town town){
        Town townDB = townR.getById(town.getId()).orElseThrow(()-> new CustomException("No se encontró el municipio"));
        Country countryDB = countryR.getById(town.getCountry().getId()).orElseThrow(()-> new CustomException("No se encontró la categoria"));
        if(town.getName() != null) {townDB.setName(town.getName());}
        if(town.getCountry() != null && town.getCountry().getId() != null) {townDB.setCountry(countryDB);}
        return townR.updateTown(townDB);
    }
    
    public void deleteTown(Integer id){
        townR.getById(id).orElseThrow(()-> new CustomException("No se encontró el municipio"));
        townR.deleteTown(id);
    }
    
}
