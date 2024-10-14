/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Motive;
import com.puntoemprende.backend.Repository.MotiveR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class MotiveS {
    
    @Autowired
    private MotiveR motiveR;
    
    public List<Motive> getAll(){
        return motiveR.getAll();
    }
    
    public Motive getById(Integer id){
        Motive motive = motiveR.getById(id).orElseThrow(()-> new CustomException("No se encontró el motivo"));
        return motive;
    }
    
    public Motive createMotive(Motive motive){
        return motiveR.createMotive(motive);
    }
    
    public Motive updateMotive(Motive motive){
        Motive motiveDB = motiveR.getById(motive.getId()).orElseThrow(()-> new CustomException("No se encontro el motivo"));
        if(motive.getName() != null) {motiveDB.setName(motive.getName());}
        if(motive.getDescription() != null) {motiveDB.setDescription(motive.getDescription());}
        return motiveR.updateMotive(motiveDB);
    }
    
    public void deleteMotive(Integer id){
        motiveR.getById(id).orElseThrow(()-> new CustomException("No se encontro el motivo"));
        motiveR.deleteMotive(id);
    }
    
}
