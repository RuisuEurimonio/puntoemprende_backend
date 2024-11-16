/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "redsocial")
@Data
public class SocialMedia extends CommonFields{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idRedSocial", nullable = false)
    private Integer id;
    
    @Column(name = "link", nullable = false)
    @Size(min = 10, max = 200, message = "Ingrese un nombre link valido", groups = {onUpdate.class, onCreate.class})
    private String link;
    
    @ManyToMany(mappedBy = "socialMedia")
    @JsonIgnore
    private List<User> users;
}
