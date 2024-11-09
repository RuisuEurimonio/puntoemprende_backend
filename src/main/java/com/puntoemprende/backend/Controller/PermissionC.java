/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.Motive;
import com.puntoemprende.backend.Model.Permission;
import com.puntoemprende.backend.Service.MotiveS;
import com.puntoemprende.backend.Service.PermissionS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import validations.onCreate;
import validations.onUpdate;

/**
 *
 * @author Ruisu's
 */
@RestController
@RequestMapping("/api/permission")
@CrossOrigin
public class PermissionC {
    
    @Autowired
    private PermissionS permissionS;
    
    @GetMapping("/all")
    public ResponseEntity<List<Permission>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(permissionS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Permission> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(permissionS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<Permission> createPermission(@Validated(onCreate.class) @RequestBody Permission motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionS.createPermission(motive));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Permission> updatePermission(@Validated(onUpdate.class) @RequestBody Permission motive){
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionS.updatePermission(motive));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable("id") Integer id){
        permissionS.deletePermission(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Permiso eliminado con id; "+id);
    }
    
}
