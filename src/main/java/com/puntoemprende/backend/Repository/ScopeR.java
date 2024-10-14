/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.ScopeJR;
import com.puntoemprende.backend.Model.Scope;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class ScopeR {
      
    @Autowired
    private ScopeJR scopeJR;
    
    public List<Scope> getAll(){
        return scopeJR.findAll();
    }
    
    public Optional<Scope> getById(int id){
        return scopeJR.findById(id);
    }    
    
    public Scope createCategory(Scope scope){
        return scopeJR.save(scope);
    }
    
    public Scope updateCategory(Scope scope){
        return scopeJR.save(scope);
    }
    
    public void deleteCategory(int id){
        scopeJR.deleteById(id);
    }
    
}
