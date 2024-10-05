/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.TypeDocument;
import com.puntoemprende.backend.Repository.TypeDocumentR;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class TypeDocumentS {
    
    @Autowired
    private TypeDocumentR typeDocumentR;
    
    public List<TypeDocument> getTypes(){
        return typeDocumentR.getTypes();
    }
    
    public Optional<TypeDocument> getType(Integer id){
        if(id != null){
            return typeDocumentR.getType(id);
        }
        throw new CustomException("El id es nulo");
    }
    
    public TypeDocument createType(TypeDocument type){
        if(type != null && type.getName() != null && type.getPrefix() != null && type.getDescription() != null){
            return typeDocumentR.createType(type);
        }
        throw new CustomException("Información incompleta");
    }
    
    public TypeDocument updateType(TypeDocument type){
        Optional<TypeDocument> typeDB = typeDocumentR.getType(type.getId());
        if(typeDB.isPresent()){
            if(type.getName() != null){
                typeDB.get().setName(type.getName());
            }
            if(type.getPrefix() != null){
                typeDB.get().setPrefix(type.getPrefix());
            }
            if(type.getDescription() != null){
                typeDB.get().setDescription(type.getDescription());
            }
        }
        throw new CustomException("No se encontro el tipo a actualizar");
    }
    
    public void deleteType(Integer id){
        if(id != null){
            Optional<TypeDocument> typeDB = typeDocumentR.getType(id);
            if(typeDB.isEmpty()){
                throw new CustomException("No se encontro el tipo a eliminar");
            }
            typeDocumentR.deleteType(id);
        }
    }
}
