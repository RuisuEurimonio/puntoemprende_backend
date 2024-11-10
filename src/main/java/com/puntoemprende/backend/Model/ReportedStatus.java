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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import validations.onCreate;

/**
 *
 * @author Ruisu's
 */
@Entity
@Table(name = "reportado")
@Data
public class ReportedStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReportado", nullable = false)
    private Integer id;
    
    @Column(name = "isHabilitado", nullable = false)
    @NotNull(message = "Seleccione un estado", groups = onCreate.class)
    private Boolean isAvailable;
    
    @Column(name = "mensajeRehabilitacion", nullable = false)
    @Size(min=5,max=250, message = "Ingrese un mensaje valido.", groups = onCreate.class)
    @NotNull(message = "Ingrese un mensaje", groups = onCreate.class)
    private String messageRehabilitation;
    
    @Column(name = "fechaCreacion", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;
    
    @Column(name = "fechaActualizacion", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;
    
    @OneToMany(mappedBy = "reportedStatus")
    @JsonIgnoreProperties("reportedStatus")
    private List<Post> posts;
}
