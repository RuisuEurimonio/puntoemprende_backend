/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Controller;

import com.puntoemprende.backend.Model.ReportedStatus;
import com.puntoemprende.backend.Service.ReportedStatusS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import validations.onCreate;
import validations.onUpdate;

/**
 *
 * @author Ruisu's
 */
@RestController
@RequestMapping("/api/reported-status")
@CrossOrigin
public class ReportedStatusC {
    
    @Autowired
    private ReportedStatusS reportedStatusS;
    
    @GetMapping("/all")
    public ResponseEntity<List<ReportedStatus>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(reportedStatusS.getAll());
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<ReportedStatus> getById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(reportedStatusS.getById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<ReportedStatus> createReportedStatus(@Validated(onCreate.class) @RequestBody ReportedStatus reportedStatus){
        return ResponseEntity.status(HttpStatus.CREATED).body(reportedStatusS.createReportedStatus(reportedStatus));
    }
    
    @PutMapping("/update")
    public ResponseEntity<ReportedStatus> updateReportedStatus(@Validated(onUpdate.class) @RequestBody ReportedStatus reportedStatus){
        return ResponseEntity.status(HttpStatus.CREATED).body(reportedStatusS.updateReportedStatus(reportedStatus));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReportedStatus(@PathVariable("id") Integer id){
        reportedStatusS.deleteReportedStatus(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Estado de reporte eliminado con id; "+id);
    }
    
}
