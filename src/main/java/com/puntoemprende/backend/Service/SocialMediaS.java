/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.SocialMedia;
import com.puntoemprende.backend.Repository.SocialMediaR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class SocialMediaS {
    
    @Autowired
    private SocialMediaR socialMediaR;
    
    public List<SocialMedia> getAll(){
        return socialMediaR.getAll();
    }
    
    public SocialMedia getById(Integer id){
        SocialMedia socialMedia = socialMediaR.getById(id).orElseThrow(()-> new CustomException("No se encontró la red social"));
        return socialMedia;
    }
    
    public SocialMedia createScope(SocialMedia socialMedia){
        return socialMediaR.createCategory(socialMedia);
    }
    
    public SocialMedia updateScope(SocialMedia socialMedia){
        SocialMedia scopeDB = socialMediaR.getById(socialMedia.getId()).orElseThrow(()-> new CustomException("No se encontró la red social"));
        if(socialMedia.getName() != null) {scopeDB.setName(socialMedia.getName());}
        if(socialMedia.getDescription() != null) {scopeDB.setDescription(socialMedia.getDescription());}
        if(socialMedia.getLink() != null) {scopeDB.setLink(socialMedia.getLink());}
        return socialMediaR.updateCategory(scopeDB);
    }
    
    public void deleteScope(Integer id){
        socialMediaR.getById(id).orElseThrow(()-> new CustomException("No se encontró la red social"));
        socialMediaR.deleteCategory(id);
    }
    
}
