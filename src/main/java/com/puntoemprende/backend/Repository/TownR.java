/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.TownJR;
import com.puntoemprende.backend.Model.Town;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class TownR {
    
    
    @Autowired
    private TownJR townJR;
    
    public List<Town> getAll(){
        return townJR.findAll();
    }
    
    public Optional<Town> getById(int id){
        return townJR.findById(id);
    }
    
    public Town createTown(Town town){
        return townJR.save(town);
    }
    
    public Town updateTown(Town town){
        return townJR.save(town);
    }
    
    public void deleteTown(int id){
        townJR.deleteById(id);
    }
    
}
