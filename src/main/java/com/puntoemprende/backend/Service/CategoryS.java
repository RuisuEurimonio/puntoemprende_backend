/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Category;
import com.puntoemprende.backend.Repository.CategoryR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class CategoryS {
    
    
    @Autowired
    private CategoryR categoryR;
    
    public List<Category> getAll(){
        return categoryR.getAll();
    }
    
    public Category getById(Integer id){
        Category category = categoryR.getById(id).orElseThrow(()-> new CustomException("No se encontró la categoria"));
        return category;
    }
    
    public Category createCategory(Category category){
        return categoryR.createCategory(category);
    }
    
    public Category updateCategory(Category category){
        Category categoryDB = categoryR.getById(category.getId()).orElseThrow(()-> new CustomException("No se encontro la categoria"));
        if(category.getName() != null) {categoryDB.setName(category.getName());}
        if(category.getDescription() != null) {categoryDB.setDescription(category.getDescription());}
        return categoryR.updateCategory(categoryDB);
    }
    
    public void deleteCategory(Integer id){
        categoryR.getById(id).orElseThrow(()-> new CustomException("No se encontro la categoria"));
        categoryR.deleteCategory(id);
    }
    
}
