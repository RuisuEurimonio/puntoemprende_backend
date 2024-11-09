/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Motive;
import com.puntoemprende.backend.Model.Permission;
import com.puntoemprende.backend.Repository.MotiveR;
import com.puntoemprende.backend.Repository.PermissionR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class PermissionS {
    
    @Autowired
    private PermissionR permissionR;
    
    public List<Permission> getAll(){
        return permissionR.getAll();
    }
    
    public Permission getById(Integer id){
        Permission permission = permissionR.getById(id).orElseThrow(()-> new CustomException("No se encontró el permiso"));
        return permission;
    }
    
    public Permission createPermission(Permission permission){
        return permissionR.createPermission(permission);
    }
    
    public Permission updatePermission(Permission permission){
        Permission permissionDB = permissionR.getById(permission.getId()).orElseThrow(()-> new CustomException("No se encontro el permiso"));
        if(permission.getName() != null) {permissionDB.setName(permission.getName());}
        return permissionR.updatePermission(permissionDB);
    }
    
    public void deletePermission(Integer id){
        permissionR.getById(id).orElseThrow(()-> new CustomException("No se encontro el permiso"));
        permissionR.deletePermission(id);
    }
}
