/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Ruisu's
 */
@Entity
@Table(name = "tipodocumento")
@Data
public class TypeDocument {
    
    @Id
    private Integer id;
    
    @Column(name = "nombre", nullable = false)
    @Size(min = 5, max= 60, message="Ingresa un nombre válido.")
    @NotNull(message="Ingrese un texto")
    @NotBlank(message="Ingrese un texto")
    private String name;
    
    @Column(name = "prefijo", nullable = false)
    @Size(min = 1, max= 5, message="Ingresa un prefijo válido.")
    @NotNull(message="Ingrese un texto")
    @NotBlank(message="Ingrese un texto")
    private String prefix;
    
    @Column(name = "descripcion", nullable= false)
    @Size(min = 5, max= 125, message="Ingresa un nombre válido.")
    @NotNull(message="Ingrese un texto")
    @NotBlank(message="Ingrese un texto")
    private String description;
    
}
