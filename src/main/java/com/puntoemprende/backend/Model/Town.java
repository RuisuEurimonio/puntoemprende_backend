/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "municipio")
@Data
public class Town {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idMunicipio", nullable = false)
    private Integer id;
    
    @Column(name = "nombre", nullable = false)
    @Size(min = 1, max = 45, message = "Ingrese un nombre válido", groups = {onUpdate.class, onCreate.class})
    private String name;
    
    @JoinColumn(name = "pais_idPais", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="towns")
    private Country country;
    
    @OneToMany(mappedBy = "town")
    @JsonIgnore
    private List<User> users;
}
