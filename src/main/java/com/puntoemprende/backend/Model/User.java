/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import validations.onCreate;
import validations.onUpdate;

/**
 *
 * @author Ruisu's
 */
@Entity
@Table(name="usuario")
@Data
public class User implements UserDetails{
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Integer id;
    
    @Column(name="nombre", nullable = false)
    @Size(min=3,max=40, message="Nombre invalido", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso un nombre", groups = onCreate.class)
    @NotBlank(message="No se ingreso un nombre", groups = onCreate.class)
    private String name;
    
    @Column(name="apellido", nullable = false)
    @Size(min=3,max=40, message="Apellido invalido", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso un apellido", groups = onCreate.class)
    @NotBlank(message="No se ingreso un apellido", groups = onCreate.class)
    private String lastName;
    
    @Column(name="correo", nullable = false)
    @Size(min=3,max=250, message="Email invalido", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso un email", groups = onCreate.class)
    @NotBlank(message="No se ingreso un email", groups = onCreate.class)
    private String email;
    
    @Column(name="emprendimiento", nullable = true)
    @Size(min=3,max=50, message="Emprendimiento invalido", groups = {onCreate.class, onUpdate.class})
    private String business;
    
    @Column(name="direccion", nullable = false)
    @Size(min=3,max=45, message="Direccion invalida", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso una dirección", groups = onCreate.class)
    @NotBlank(message="No se ingreso una dirección", groups = onCreate.class)
    private String address;
    
    @Column(name="numeroDocumento", nullable = false)
    @Size(min=3,max=20, message="Documento invalido", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso un documento", groups = onCreate.class)
    @NotBlank(message="No se ingreso un documento", groups = onCreate.class)
    private String document;
    
    @Column(name="contrasena", nullable = false)
    @Size(min=8,max=30, message="Contraseña invalida", groups = {onCreate.class, onUpdate.class})
    @NotNull(message="No se ingreso una contraseña", groups = onCreate.class)
    @NotBlank(message="No se ingreso una contraseña", groups = onCreate.class)
    private String password;
    
    @Column(name="isAutenticado", nullable = false)
    private boolean isAutenticated;
    
    @JoinColumn(name = "Municipio_idMunicipio", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="users")
    private Town town;
    
    @JoinColumn(name = "TipoDocumento_idTipoDocumento", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="users")
    private TypeDocument typeDocument;
    
    @JoinColumn(name = "Permiso_idPermiso", nullable = false)
    @ManyToOne()
    @JsonIgnoreProperties(value="users")
    private Permission permission;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Post> posts;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "redsocial_has_usuario", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "Usuario_idUsuario"), // Llave foránea de la tabla user
        inverseJoinColumns = @JoinColumn(name = "RedSocial_idRedSocial") // Llave foránea de la tabla socialmedia
    )
    private List<SocialMedia> socialMedia;
    
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+permission.getName());
        return Collections.singletonList(authority);
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
