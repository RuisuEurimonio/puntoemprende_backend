/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Permission;
import com.puntoemprende.backend.Model.SocialMedia;
import com.puntoemprende.backend.Model.Town;
import com.puntoemprende.backend.Model.TypeDocument;
import com.puntoemprende.backend.Model.User;
import com.puntoemprende.backend.Repository.PermissionR;
import com.puntoemprende.backend.Repository.SocialMediaR;
import com.puntoemprende.backend.Repository.TownR;
import com.puntoemprende.backend.Repository.TypeDocumentR;
import com.puntoemprende.backend.Repository.UserR;
import com.puntoemprende.backend.Security.JwtService;
import com.puntoemprende.backend.Security.SecurityBeansConfig;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
@RequiredArgsConstructor
public class UserS {
    
    @Autowired
    private UserR userR;
    
    @Autowired
    private TownR townR;
    
    @Autowired
    private TypeDocumentR typeDocumentR;
    
    @Autowired
    private PermissionR permissionR;
    
    @Autowired
    private SocialMediaR socialMediaR;
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired 
    private JwtService jwtS; 
    
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userR.getAll();
    }
    
    public Optional<User> findByEmail(String email){
        return userR.findByEmail(email);
    }

    public User getUser(Integer id) {
        User user = userR.getById(id).orElseThrow(()-> new CustomException("No se encontro el usuario"));
        return user;
    }

    public User createUser(User user) {
        Optional<User> userDB = userR.findByEmail(user.getEmail());
        if(userDB.isPresent()){
            System.out.println("hola");
            throw new CustomException("El correo ya esta registrado.");
        }
        if(user.getPermission() == null){
            user.setPermission(permissionR.getById(1).orElseThrow(()-> new CustomException("Ha sucedido un error")));
        }
        if(user.getSocialMedia() != null){
            List<SocialMedia> socialList = new ArrayList<>();
            for(SocialMedia social : user.getSocialMedia()){
                Optional<SocialMedia> socialDB = socialMediaR.getById(social.getId());
                if(socialDB.isEmpty() && social.getId() == null){
                    socialList.add(socialMediaR.createCategory(social));
                }else{
                    socialList.add(socialDB.get());
                }
            }
            
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userR.createUser(user);

    }

    public User updateUser(User user) {
        Optional<User> userDB = userR.getById(user.getId());
        if (userDB.isEmpty()) {
            throw new CustomException("No se encontro el usuario a actualizar");
        }
        if (user.getName() != null) {
            userDB.get().setName(user.getName());
        }
        if (user.getLastName()!= null) {
            userDB.get().setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            userDB.get().setEmail(user.getEmail());
        }
        if (user.getDocument()!= null) {
            userDB.get().setDocument(user.getDocument());
        }
        if (user.getBusiness() != null) {
            userDB.get().setBusiness(user.getBusiness());
        }
        if (user.getAddress() != null) {
            userDB.get().setAddress(user.getAddress());
        }
        if (user.getTown() != null) {
            Town townDB = townR.getById(user.getTown().getId()).orElseThrow(()-> new CustomException("No se encontro la ciudad"));
            userDB.get().setTown(townDB);
        }
        if(user.getTypeDocument() != null){
            TypeDocument typeDocumentDB = typeDocumentR.getType(user.getTypeDocument().getId()).orElseThrow(()-> new CustomException("No se encontro el tipo de documento"));
            userDB.get().setTypeDocument(typeDocumentDB);
        }
        if(user.getPermission()!= null){
            Permission permissionDB = permissionR.getById(user.getTypeDocument().getId()).orElseThrow(()-> new CustomException("No se encontro el permiso"));
            userDB.get().setPermission(permissionDB);
        }
        if(user.getPassword() != null){
            userDB.get().setPassword(user.getPassword());
        }
        if(user.getSocialMedia() != null){
            List<SocialMedia> socialMediaList = new ArrayList<>(userDB.get().getSocialMedia());
            for(SocialMedia social : user.getSocialMedia()){
                if(social.getId() == null){
                    socialMediaList.add(socialMediaR.createCategory(social));
                }else{
                    socialMediaList.add(socialMediaR.getById(social.getId()).orElseThrow(()-> new CustomException("No se encontro la red social")));
                }
            }
            userDB.get().setSocialMedia(socialMediaList);
        }
        userDB.get().setAutenticated(true);
        return userR.updateUser(userDB.get());

    }

    public void deleteUser(Integer id) {
        if (id != null) {
            Optional<User> typeDB = userR.getById(id);
            if (typeDB.isEmpty()) {
                throw new CustomException("No se encontro el usuario a eliminar");
            }
            userR.deleteUser(id);
            return;
        }
        throw new CustomException("No se ingreso un id");
    }
    
    public Map<String, Object> login(String correo, String contrasena) throws NoSuchAlgorithmException{
        
        Map<String, Object> res = new HashMap<String,Object>();
        
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(correo, contrasena);
        authManager.authenticate(auth);
        User user = userR.findByEmail(correo).get();
        
        String token = jwtS.generateKey(user, generateExtraClaims(user));
        res.put("User", user);
        res.put("Token", token);
        
        return res;
    }
    
    public Map<String, Object> generateExtraClaims(User usuario){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", usuario.getName()+" "+usuario.getLastName());
        extraClaims.put("Authorities", usuario.getAuthorities());   
        return extraClaims;
    }
    
}
