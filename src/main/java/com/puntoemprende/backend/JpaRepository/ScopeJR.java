/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.JpaRepository;

import com.puntoemprende.backend.Model.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ruisu's
 */
public interface ScopeJR extends JpaRepository<Scope, Integer>{
    
}