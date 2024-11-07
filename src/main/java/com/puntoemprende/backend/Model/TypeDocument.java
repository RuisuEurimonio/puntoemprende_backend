/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import validations.onCreate;
import validations.onUpdate;

/**
 *
 * @author Ruisu's
 */
@Entity
@Table(name = "tipodocumento")
@Data
public class TypeDocument {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTipoDocumento")
    private Integer id;
    
    @Column(name = "nombre", nullable = false)
    @NotNull(message="Ingrese un texto", groups = onCreate.class)
    @NotBlank(message="Ingrese un texto", groups = onCreate.class)
    @Size(min = 5, max= 60, message="Ingresa un nombre válido.", groups = {onCreate.class, onUpdate.class})
    private String name;
    
    @Column(name = "prefijo", nullable = false)
    @NotNull(message="Ingrese un texto", groups = onCreate.class)
    @NotBlank(message="Ingrese un texto", groups = onCreate.class)
    @Size(min = 1, max= 5, message="Ingresa un prefijo válido.", groups = {onCreate.class, onUpdate.class})
    private String prefix;
    
    @Column(name = "descripcion", nullable= false)
    @NotNull(message="Ingrese un texto", groups = onCreate.class)
    @NotBlank(message="Ingrese un texto", groups = onCreate.class)
    @Size(min = 5, max= 125, message="Ingresa un nombre válido.", groups = {onCreate.class, onUpdate.class})
    private String description;
    
    @OneToMany(mappedBy = "typeDocument")
    @JsonIgnoreProperties("typeDocument")
    private List<User> users;
}
