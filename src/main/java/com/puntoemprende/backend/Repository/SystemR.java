/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.SystemJR;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.puntoemprende.backend.Model.System;

/**
 *
 * @author Ruisu's
 */
@Repository
public class SystemR {
    
    @Autowired
    private SystemJR systemJR;
    
    public List<System> getAll(){
        return systemJR.findAll();
    }
    
    public Optional<System> getById(int id){
        return systemJR.findById(id);
    }    
    
    public System createSystem(System system){
        return systemJR.save(system);
    }
    
    public System updateSystem(System system){
        return systemJR.save(system);
    }
    
    public void deleteSystem(int id){
        systemJR.deleteById(id);
    }
    
}
