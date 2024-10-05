/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.TypeDocumentJR;
import com.puntoemprende.backend.Model.TypeDocument;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class TypeDocumentR {
    
    @Autowired
    private TypeDocumentJR typeDocumentJr;
    
    public List<TypeDocument> getTypes(){
        return typeDocumentJr.findAll();
    }
    
    public Optional<TypeDocument> getType(int id){
        return typeDocumentJr.findById(id);
    }
    
    public TypeDocument createType(TypeDocument type){
        return typeDocumentJr.save(type);
    }
    
    public TypeDocument updateType(TypeDocument type){
        return typeDocumentJr.save(type);
    }
    
    public void deleteType(int id){
        typeDocumentJr.deleteById(id);
    }
}
