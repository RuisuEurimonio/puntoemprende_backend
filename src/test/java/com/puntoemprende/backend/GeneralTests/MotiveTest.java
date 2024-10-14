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
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class MotiveTest {
    
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getList_returnIsOk() throws Exception{
        mockMvc.perform(get("/api/motive/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("El contenido mostrado no cumple con las normas establecidas."))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void create_returnIsCreated() throws Exception{
        String newType = "{\"name\": \"Desinformación\", \"description\": \"Tiene contenido que no es veridico ni fiable.\"}";
        
        mockMvc.perform(post("/api/motive/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Desinformación"))
                .andExpect(jsonPath("$.id").value(2));
                
    }
    
    @Test
    public void getWithoutId_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/motive/id/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
    }
    
    @Test
    public void getThatNotExist_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/motive/id/5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el motivo"));
    }
    
    @Test
    public void createWithoutData_returnBadRequest() throws Exception{
        String newType = "{}";
        
        mockMvc.perform(post("/api/motive/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Ingrese un nombre"))
                .andExpect(jsonPath("$.description").value("Ingrese una descripcion"));
                
    }
    
    @Test
    public void createWithoutName_returnBadRequest() throws Exception{
        String newType = "{\"description\": \"Tiene contenido que no es veridico ni fiable.\"}";
        
        mockMvc.perform(post("/api/motive/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Ingrese un nombre"));
    }
    
    @Test
    public void updateMotiveWithAllData_returnOk() throws Exception{
        String newType = "{\"id\": 1, \"name\": \"Desinformación\", \"description\": \"Tiene contenido que no es veridico ni fiable.\"}";
        String expectedType = "{\"id\": 1, \"name\": \"Desinformación\", \"description\": \"Tiene contenido que no es veridico ni fiable.\"}";
        
        mockMvc.perform(put("/api/motive/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedType));
                
    }
    
    @Test
    public void updateWithAllDataButWrongId_returnBadRequest() throws Exception{
        String newType = "{\"id\": 100, \"name\": \"Desinformación\", \"description\": \"Tiene contenido que no es veridico ni fiable.\"}";
        
        mockMvc.perform(put("/api/motive/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontro el motivo"));
                
    }
    
    @Test
    public void delete_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/motive/delete/23"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontro el motivo"));
                
    }
    
    @Test
    public void deleteWithoutUrlId_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/motive/update/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
                
    }
    
    @Test
    public void delete_isNoContent() throws Exception{
        mockMvc.perform(delete("/api/motive/delete/1"))
                .andExpect(status().isNoContent());
                
    }
    
}
