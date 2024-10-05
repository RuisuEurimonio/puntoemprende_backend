/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.TypeDocument;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Ruisu's
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class TypeDocumentTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getEmptyListOfType_returnIsOk() throws Exception{
        mockMvc.perform(get("/api/typedocument/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].prefix").value("TI"))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void createType_returnIsCreated() throws Exception{
        String newType = "{\"name\": \"Cedula de ciudanania\", \"prefix\": \"CC\", \"description\": \"Documento utilizado por las personas con mayoria de edad en colombia.\"}";
        
        mockMvc.perform(post("/api/typedocument/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Cedula de ciudanania"))
                .andExpect(jsonPath("$.id").value(2));
                
    }
    
    @Test
    public void getTypeWithoutId_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/typedocument/id/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
    }
    
    @Test
    public void getTypeThatNotExist_returnIsBadRequest() throws Exception{
        mockMvc.perform(get("/api/typedocument/id/5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El id no se encontro"));
    }
    
    @Test
    public void createTypeWithoutData_returnBadRequest() throws Exception{
        String newType = "{}";
        
        mockMvc.perform(post("/api/typedocument/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.prefix").value("Ingrese un texto"))
                .andExpect(jsonPath("$.name").value("Ingrese un texto"))
                .andExpect(jsonPath("$.description").value("Ingrese un texto"));
                
    }
    
    @Test
    public void createTypeWithoutName_returnBadRequest() throws Exception{
        String newType = "{\"prefix\": \"CC\", \"description\": \"Documento utilizado por las personas con mayoria de edad en colombia.\"}";
        
        mockMvc.perform(post("/api/typedocument/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Ingrese un texto"));
                
    }
    
    @Test
    public void updateTypeWithAllData_returnOk() throws Exception{
        String newType = "{\"id\": 1, \"name\": \"Registro civil\", \"prefix\": \"RC\", \"description\": \"Documento que tienen todas las peronas nacidas en colombia.\"}";
        String expectedType = "{\"id\": 1, \"name\": \"Registro civil\", \"prefix\": \"RC\", \"description\": \"Documento que tienen todas las peronas nacidas en colombia.\"}";
        
        mockMvc.perform(put("/api/typedocument/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedType));
                
    }
    
    @Test
    public void updateTypeWithAllDataButWrongId_returnBadRequest() throws Exception{
        String newType = "{\"id\": 24, \"name\": \"Registro civil\", \"prefix\": \"RC\", \"description\": \"Documento que tienen todas las peronas nacidas en colombia.\"}";
        
        mockMvc.perform(put("/api/typedocument/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newType))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontro el tipo a actualizar"));
                
    }
    
    @Test
    public void delete_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/typedocument/delete/23"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No se encontro el tipo a eliminar"));
                
    }
    
    @Test
    public void deleteWithoutUrlId_isBadRequest() throws Exception{
        mockMvc.perform(delete("/api/typedocument/update/"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ha sucedido un error"));
                
    }
    
    @Test
    public void delete_isNoContent() throws Exception{
        mockMvc.perform(delete("/api/typedocument/delete/1"))
                .andExpect(status().isNoContent());
                
    }
}
