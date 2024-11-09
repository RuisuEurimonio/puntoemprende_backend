/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.MotiveJR;
import com.puntoemprende.backend.Model.Motive;
import com.puntoemprende.backend.Model.Permission;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.puntoemprende.backend.JpaRepository.PermissionJR;

/**
 *
 * @author Ruisu's
 */
@Repository
public class PermissionR {
    
    @Autowired
    private PermissionJR permisosJr;
    
    public List<Permission> getAll(){
        return permisosJr.findAll();
    }
    
    public Optional<Permission> getById(int id){
        return permisosJr.findById(id);
    }    
    
    public Permission createPermission (Permission permiso){
        return permisosJr.save(permiso);
    }
    
    public Permission updatePermission(Permission permiso){
        return permisosJr.save(permiso);
    }
    
    public void deletePermission(int id){
        permisosJr.deleteById(id);
    }
}
