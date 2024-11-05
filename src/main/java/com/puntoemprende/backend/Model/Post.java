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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import validations.onCreate;
import validations.onUpdate;

/**
 *
 * @author Ruisu's
 */
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublicacion", nullable = false)
    private Integer id;
    
    @Column(name = "titulo", nullable = false)
    @Size(min = 4, max = 45, message="Ingrese un título", groups = {onCreate.class, onUpdate.class})
    @NotEmpty(message = "Ingrese un título", groups = onCreate.class)
    @NotNull(message = "Ingrese un título", groups = onCreate.class)
    private String title;
    
    @Column(name = "precio", nullable = false)
    @NotEmpty(message = "Ingrese un precio", groups = onCreate.class)
    @NotNull(message = "Ingrese un precio", groups = onCreate.class)
    private double price;
    
    @Column(name = "cantidad", nullable = true)
    private int cant;
    
    @Column(name = "descripcion", nullable = false)
    @Size(min = 1, max = 200, message = "Ingrese una descripcion validá", groups = {onCreate.class, onUpdate.class})
    @NotEmpty(message = "Ingrese una descripción", groups = onCreate.class)
    @NotNull(message = "Ingrese una descripción", groups = onCreate.class)
    private String description;
    
    @Column(name = "imagen", nullable = true)
    private String image;
    
    @Column(name = "haveEnvio", nullable = false)
    private Boolean haveSend;
    
    @Column(name = "fechaCreacion", nullable = false)
    @UpdateTimestamp
    private LocalDateTime creationDate;
    
    @Column(name = "fechaActualizacion", nullable = false)
    @CreationTimestamp
    private LocalDateTime updateDate;
    
    //To do
    //private Usuario user;
    private ReportedStatus reportedStatus;
    private Scope scope;
    private Category category;
    private Motive motive;
    
}
