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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
@Entity
@Table(name = "publicacion")
@Data
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublicacion", nullable = false)
    private Integer id;
    
    @Column(name = "titulo", nullable = false)
    @Size(min = 2, max = 45, message="Ingrese un título", groups = {onCreate.class, onUpdate.class})
    @NotEmpty(message = "Ingrese un título", groups = onCreate.class)
    @NotNull(message = "Ingrese un título", groups = onCreate.class)
    private String title;
    
    @Column(name = "precio", nullable = false)
    @Min(1)
    private Double price;
    
    @Column(name = "cantidad", nullable = true)
    private Integer cant;
    
    @Column(name = "descripcion", nullable = false)
    @Size(min = 1, max = 200, message = "Ingrese una descripcion validá", groups = {onCreate.class, onUpdate.class})
    @NotEmpty(message = "Ingrese una descripción", groups = onCreate.class)
    @NotNull(message = "Ingrese una descripción", groups = onCreate.class)
    private String description;
    
    @Column(name = "imagen", nullable = true)
    @Size(min = 1, max = 300, message = "Ingrese una imagen validá", groups = {onCreate.class, onUpdate.class})
    private String image;
    
    @Column(name = "haveEnvio", nullable = false)
    private Boolean haveSend;
    
    @Column(name = "fechaCreacion", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;
    
    @Column(name = "fechaActualizacion", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;
    
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="posts")
    private User user;
    
    @JoinColumn(name = "Reportado_idReportado", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="posts")
    private ReportedStatus reportedStatus;
    
    @JoinColumn(name = "Alcance_idAlcance", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="posts")
    private Scope scope;
    
    @JoinColumn(name = "Categoria_idCategoria", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="posts")
    private Category category;
    
    @JoinColumn(name = "Motivo_idMotivo", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="posts")
    private Motive motive;
    
}
