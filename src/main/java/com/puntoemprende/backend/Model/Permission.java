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
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "Permisos")
@Data
public class Permission {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPermisos", nullable = false)
    private Integer id;
    
    @Column(name = "nombre", nullable = false)
    @Size(min = 4, max = 45, message="Ingrese un nombre", groups = {onCreate.class, onUpdate.class})
    @NotEmpty(message = "Ingrese un nombre", groups = onCreate.class)
    @NotNull(message = "Ingrese un nombre", groups = onCreate.class)
    private String name;
    
    @OneToMany(mappedBy = "permission")
    @JsonIgnoreProperties("permission")
    private List<User> users;
}
