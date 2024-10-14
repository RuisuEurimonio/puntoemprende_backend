/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
@Data
public class CommonFields {
    
    @Column(name = "nombre", nullable = false)
    @Size(min = 5, max = 45, message = "Ingrese un nombre valido", groups = {onUpdate.class, onCreate.class})
    @NotBlank(message = "Ingrese un nombre", groups = onCreate.class)
    @NotNull(message = "Ingrese un nombre", groups = onCreate.class)
    private String name;
    
    @Column(name = "descripcion", nullable = false)
    @Size(min = 5, max = 200, message = "Ingrese una descripcion valida", groups = {onUpdate.class, onCreate.class})
    @NotBlank(message = "Ingrese una descripcion", groups = onCreate.class)
    @NotNull(message = "Ingrese una descripcion", groups = onCreate.class)
    private String description;
    
}
