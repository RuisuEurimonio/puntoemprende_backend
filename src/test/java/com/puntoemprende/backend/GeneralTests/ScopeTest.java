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
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ScopeTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getList_returnIsOk() throws Exception{
        mockMvc.perform(get("/api/scope/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Hace referencia a un rango de personas que se encuentran relativamente cerca."))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void create_returnIsCreated() throws Exception{
        String newType = "{\"name\": \"Nacional\", \"description\": \"Hace referencia a un alcance de publico de manera nacional, en el mismo pais\"}";
        
        mockMvc.perform(post("/api/scope/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Nacional"))
                .andExpect(jsonPath("$.id").value(2));
                
    }
    
    @Test
    public void getTypeWithoutId_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/scope/id/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
    }
    
    @Test
    public void getTypeThatNotExist_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/scope/id/5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el rango"));
    }
    
    @Test
    public void createTypeWithoutData_returnBadRequest() throws Exception{
        String newType = "{}";
        
        mockMvc.perform(post("/api/scope/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Ingrese un nombre"))
                .andExpect(jsonPath("$.description").value("Ingrese una descripcion"));
                
    }
    
    @Test
    public void createWithoutName_returnBadRequest() throws Exception{
        String newType = "{\"description\": \"Hace referencia a un alcance de publico de manera nacional, en el mismo pais.\"}";
        
        mockMvc.perform(post("/api/scope/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Ingrese un nombre"));
    }
    
    @Test
    public void updateWithAllData_returnOk() throws Exception{
        String newType = "{\"id\": 1, \"name\": \"Nacional\", \"description\": \"Hace referencia a un alcance de publico de manera nacional, en el mismo pais.\"}";
        String expectedType = "{\"id\": 1, \"name\": \"Nacional\", \"description\": \"Hace referencia a un alcance de publico de manera nacional, en el mismo pais.\"}";
        
        mockMvc.perform(put("/api/scope/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedType));
                
    }
    
    @Test
    public void updateWithAllDataButWrongId_returnBadRequest() throws Exception{
        String newType = "{\"id\": 100, \"name\": \"Nacional\", \"description\": \"Hace referencia a un alcance de publico de manera nacional, en el mismo pais.\"}";
        
        mockMvc.perform(put("/api/scope/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el rango"));
                
    }
    
    @Test
    public void delete_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/scope/delete/23"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el rango"));
                
    }
    
    @Test
    public void deleteWithoutUrlId_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/scope/update/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
                
    }
    
    @Test
    public void delete_isNoContent() throws Exception{
        mockMvc.perform(delete("/api/scope/delete/1"))
                .andExpect(status().isNoContent());
                
    }
    
}

