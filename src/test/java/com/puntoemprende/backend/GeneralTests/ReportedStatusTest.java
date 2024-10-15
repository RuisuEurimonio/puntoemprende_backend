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
@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ReportedStatusTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getList_returnIsOk() throws Exception{
        mockMvc.perform(get("/api/reported-status/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].messageRehabilitation").value("Se comprobo que el contenido si cumple con las normas."))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void create_returnIsCreated() throws Exception{
        String newType = "{\"isAvailable\": \"False\", \"messageRehabilitation\": \"Se comprobo que el contenido si cumple con las normas.\"}";
        
        mockMvc.perform(post("/api/reported-status/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isAvailable").value("false"))
                .andExpect(jsonPath("$.id").value(2));
                
    }
    
    @Test
    public void getWithoutId_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/reported-status/id/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
    }
    
    @Test
    public void getThatNotExist_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/reported-status/id/5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el reporte"));
    }
    
    @Test
    public void createWithoutData_returnBadRequest() throws Exception{
        String newType = "{}";
        
        mockMvc.perform(post("/api/reported-status/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.isAvailable").value("Seleccione un estado"))
                .andExpect(jsonPath("$.messageRehabilitation").value("Ingrese un mensaje"));
                
    }
    
    @Test
    public void createWithoutName_returnBadRequest() throws Exception{
        String newType = "{\"messageRehabilitation\": \"Se comprobo que el contenido si cumple con las normas.\"}";
        
        mockMvc.perform(post("/api/reported-status/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.isAvailable").value("Seleccione un estado"));
    }
    
    @Test
    public void updateMotiveWithAllData_returnOk() throws Exception{
        String newType = "{\"id\": 1, \"isAvailable\": false, \"messageRehabilitation\": \"Se comprobo que el contenido si cumple con las normas.\"}";
        String expectedType = "{\"id\": 1, \"isAvailable\": false, \"messageRehabilitation\": \"Se comprobo que el contenido si cumple con las normas.\"}";
        
        mockMvc.perform(put("/api/reported-status/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedType));
                
    }
    
    @Test
    public void updateWithAllDataButWrongId_returnBadRequest() throws Exception{
        String newType = "{\"id\": 100, \"isAvailable\": false, \"messageRehabilitation\": \"Se comprobo que el contenido si cumple con las normas.\"}";
        
        mockMvc.perform(put("/api/reported-status/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el reporte"));
                
    }
    
    @Test
    public void delete_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/reported-status/delete/23"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontró el reporte"));
                
    }
    
    @Test
    public void deleteWithoutUrlId_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/reported-status/update/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
                
    }
    
    @Test
    public void delete_isNoContent() throws Exception{
        mockMvc.perform(delete("/api/reported-status/delete/1"))
                .andExpect(status().isNoContent());
                
    }
     
    
}
