/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.SocialMediaJR;
import com.puntoemprende.backend.Model.SocialMedia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class SocialMediaR {
    
    @Autowired
    private SocialMediaJR socialMediaJR;
    
    public List<SocialMedia> getAll(){
        return socialMediaJR.findAll();
    }
    
    public Optional<SocialMedia> getById(int id){
        return socialMediaJR.findById(id);
    }    
    
    public SocialMedia createCategory(SocialMedia socialMedia){
        return socialMediaJR.save(socialMedia);
    }
    
    public SocialMedia updateCategory(SocialMedia socialMedia){
        return socialMediaJR.save(socialMedia);
    }
    
    public void deleteCategory(int id){
        socialMediaJR.deleteById(id);
    }
    
    
}
