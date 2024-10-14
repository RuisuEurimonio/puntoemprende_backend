/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.MotiveJR;
import com.puntoemprende.backend.Model.Motive;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class MotiveR {
    
    @Autowired
    private MotiveJR motiveJr;
    
    public List<Motive> getAll(){
        return motiveJr.findAll();
    }
    
    public Optional<Motive> getById(int id){
        return motiveJr.findById(id);
    }    
    
    public Motive createMotive (Motive motive){
        return motiveJr.save(motive);
    }
    
    public Motive updateMotive(Motive motive){
        return motiveJr.save(motive);
    }
    
    public void deleteMotive(int id){
        motiveJr.deleteById(id);
    }
}
