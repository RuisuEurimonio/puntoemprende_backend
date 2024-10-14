/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.CategoryJR;
import com.puntoemprende.backend.Model.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class CategoryR {
    
    @Autowired
    private CategoryJR categoryJr;
    
    public List<Category> getAll(){
        return categoryJr.findAll();
    }
    
    public Optional<Category> getById(int id){
        return categoryJr.findById(id);
    }    
    
    public Category createCategory(Category category){
        return categoryJr.save(category);
    }
    
    public Category updateCategory(Category category){
        return categoryJr.save(category);
    }
    
    public void deleteCategory(int id){
        categoryJr.deleteById(id);
    }
    
}
