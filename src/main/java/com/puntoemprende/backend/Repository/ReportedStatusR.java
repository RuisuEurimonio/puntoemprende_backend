/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Repository;

import com.puntoemprende.backend.JpaRepository.ReportedStatusJR;
import com.puntoemprende.backend.Model.ReportedStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ruisu's
 */
@Repository
public class ReportedStatusR {
    
    @Autowired
    private ReportedStatusJR reportedStatusJr;
    
    public List<ReportedStatus> getAll(){
        return reportedStatusJr.findAll();
    }
    
    public Optional<ReportedStatus> getById(int id){
        return reportedStatusJr.findById(id);
    }    
    
    public ReportedStatus createReportedStatus(ReportedStatus reportedStatus){
        return reportedStatusJr.save(reportedStatus);
    }
    
    public ReportedStatus updateReportedStatus(ReportedStatus reportedStatus){
        return reportedStatusJr.save(reportedStatus);
    }
    
    public void deleteReportedStatus(int id){
        reportedStatusJr.deleteById(id);
    }
    
}
