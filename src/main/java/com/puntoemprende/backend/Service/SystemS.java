/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.System;
import com.puntoemprende.backend.Repository.SystemR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class SystemS {
    
    @Autowired
    private SystemR systemR;
    
    public List<System> getAll(){
        return systemR.getAll();
    }
    
    public System getById(Integer id){
        System socialMedia = systemR.getById(id).orElseThrow(()-> new CustomException("No se encontró el sistema."));
        return socialMedia;
    }
    
    public System createSystem(System system){
        List<System> systemList = systemR.getAll();
        if(systemList.size() >= 1){
            throw new CustomException("Ya existe un sistema.");
        }else{
            return systemR.createSystem(system);   
        }
    }
    
    public System updateSystem(com.puntoemprende.backend.Model.System system){
        System systemDB = systemR.getById(system.getId()).orElseThrow(()-> new CustomException("No se encontró el sistema."));
        if(system.getName() != null) {systemDB.setName(system.getName());}
        if(system.getDescription() != null) {systemDB.setDescription(system.getDescription());}
        if(system.getNote()!= null) {systemDB.setNote(system.getNote());}
        return systemR.updateSystem(systemDB);
    }
    
}