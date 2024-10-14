/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Scope;
import com.puntoemprende.backend.Repository.ScopeR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class ScopeS {
    
    @Autowired
    private ScopeR scopeR;
    
    public List<Scope> getAll(){
        return scopeR.getAll();
    }
    
    public Scope getById(Integer id){
        Scope category = scopeR.getById(id).orElseThrow(()-> new CustomException("No se encontró el rango"));
        return category;
    }
    
    public Scope createScope(Scope scope){
        return scopeR.createCategory(scope);
    }
    
    public Scope updateScope(Scope scope){
        Scope scopeDB = scopeR.getById(scope.getId()).orElseThrow(()-> new CustomException("No se encontró el rango"));
        if(scope.getName() != null) {scopeDB.setName(scope.getName());}
        if(scope.getDescription() != null) {scopeDB.setDescription(scope.getDescription());}
        return scopeR.updateCategory(scopeDB);
    }
    
    public void deleteScope(Integer id){
        scopeR.getById(id).orElseThrow(()-> new CustomException("No se encontró el rango"));
        scopeR.deleteCategory(id);
    }
    
}
