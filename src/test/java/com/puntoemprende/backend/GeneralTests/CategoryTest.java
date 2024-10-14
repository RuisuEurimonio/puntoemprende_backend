/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.GeneralTests;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ruisu's
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class CategoryTest {
    
        
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getList_returnIsOk() throws Exception{
        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Articulos que se crearon por medio de un proceso manual."))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void create_returnIsCreated() throws Exception{
        String newType = "{\"name\": \"Computo\", \"description\": \"Articulos relacionados a los computadores.\"}";
        
        mockMvc.perform(post("/api/category/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Computo"))
                .andExpect(jsonPath("$.id").value(2));
                
    }
    
    @Test
    public void getTypeWithoutId_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/category/id/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
    }
    
    @Test
    public void getTypeThatNotExist_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/category/id/5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró la categoria"));
    }
    
    @Test
    public void createTypeWithoutData_returnBadRequest() throws Exception{
        String newType = "{}";
        
        mockMvc.perform(post("/api/category/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Ingrese un nombre"))
                .andExpect(jsonPath("$.description").value("Ingrese una descripcion"));
                
    }
    
    @Test
    public void createWithoutName_returnBadRequest() throws Exception{
        String newType = "{\"description\": \"Tiene contenido que no es veridico ni fiable.\"}";
        
        mockMvc.perform(post("/api/category/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Ingrese un nombre"));
    }
    
    @Test
    public void updateMotiveWithAllData_returnOk() throws Exception{
        String newType = "{\"id\": 1, \"name\": \"Computo\", \"description\": \"Articulos relacionados a los computadores.\"}";
        String expectedType = "{\"id\": 1, \"name\": \"Computo\", \"description\": \"Articulos relacionados a los computadores.\"}";
        
        mockMvc.perform(put("/api/category/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedType));
                
    }
    
    @Test
    public void updateTypeWithAllDataButWrongId_returnBadRequest() throws Exception{
        String newType = "{\"id\": 100, \"name\": \"Computo\", \"description\": \"Articulos relacionados a los computadores.\"}";
        
        mockMvc.perform(put("/api/category/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontro la categoria"));
                
    }
    
    @Test
    public void delete_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/category/delete/23"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontro la categoria"));
                
    }
    
    @Test
    public void deleteWithoutUrlId_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/category/update/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
                
    }
    
    @Test
    public void delete_isNoContent() throws Exception{
        mockMvc.perform(delete("/api/category/delete/1"))
                .andExpect(status().isNoContent());
                
    }
     
    
}
