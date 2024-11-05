/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puntoemprende.backend.Model.System;

/**
 *
 * @author Ruisu's
 */
public interface SystemJR extends JpaRepository<System, Integer>{
    
}
