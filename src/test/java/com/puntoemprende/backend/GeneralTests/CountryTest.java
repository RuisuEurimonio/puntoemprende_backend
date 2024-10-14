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
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class CountryTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getEmptyList_returnIsOk() throws Exception{
        mockMvc.perform(get("/api/country/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Colombia"))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void create_returnIsCreated() throws Exception{
        String newType = "{\"name\": \"Mexico\", \"prefix\": \"MX\"}";
        
        mockMvc.perform(post("/api/country/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mexico"))
                .andExpect(jsonPath("$.id").value(2));
                
    }
    
    @Test
    public void getWithoutId_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/country/id/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
    }
    
    @Test
    public void getThatNotExist_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/country/id/5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el país."));
    }
    
    @Test
    public void createWithoutData_returnBadRequest() throws Exception{
        String newType = "{}";
        
        mockMvc.perform(post("/api/country/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.prefix").value("No se ingreso un prefijo"))
                .andExpect(jsonPath("$.name").value("No se ingreso un nombre"));
                
    }
    
    @Test
    public void createWithoutName_returnBadRequest() throws Exception{
        String newType = "{\"prefix\": \"MX\"}";
        
        mockMvc.perform(post("/api/country/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("No se ingreso un nombre"));
                
    }
    
    @Test
    public void updateWithAllData_returnOk() throws Exception{
        String newCountry = "{\"id\": 1, \"name\": \"Colombia\", \"prefix\": \"COL\"}";
        String expectedCountry = "{\"id\": 1, \"name\": \"Colombia\", \"prefix\": \"COL\"}";
        
        mockMvc.perform(put("/api/country/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCountry))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedCountry));
    }
    
    @Test
    public void updateTypeWithAllDataButWrongId_returnBadRequest() throws Exception{
        String newType = "{\"id\": 24, \"name\": \"Colombia\", \"prefix\": \"COL\"}";
        
        mockMvc.perform(put("/api/country/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el país a actualizar"));
                
    }
    
    @Test
    public void delete_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/country/delete/23"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el país a eliminar"));
                
    }
    
    @Test
    public void deleteWithoutUrlId_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/country/update/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
                
    }
    
    @Test
    public void delete_isNoContent() throws Exception{
        mockMvc.perform(delete("/api/country/delete/1"))
                .andExpect(status().isNoContent());
                
    }
    
}
