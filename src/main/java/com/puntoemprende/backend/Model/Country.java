/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import validations.onCreate;
import validations.onUpdate;

/**
 *
 * @author Ruisu's
 */

@Entity
@Table(name="pais")
@Data
public class Country {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPais", nullable = false)
    private Integer id;
    
    @Column(name="nombre", nullable = false)
    @Size(min=3,max=30, message="Nombre invalido", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso un nombre", groups = onCreate.class)
    @NotBlank(message="No se ingreso un nombre", groups = onCreate.class)
    private String name;
    
    @Column(name="prefijo", nullable = false)
    @Size(min=1,max=5, message="Prefijo invalido", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso un prefijo", groups = onCreate.class)
    @NotBlank(message="No se ingreso un prefijo", groups = onCreate.class)
    private String prefix;
    
    
}
