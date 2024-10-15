/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.ReportedStatus;
import com.puntoemprende.backend.Repository.ReportedStatusR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class ReportedStatusS {
    
    @Autowired
    private ReportedStatusR reportedStatusR;
    
    public List<ReportedStatus> getAll(){
        return reportedStatusR.getAll();
    }
    
    public ReportedStatus getById(Integer id){
        ReportedStatus category = reportedStatusR.getById(id).orElseThrow(()-> new CustomException("No se encontró el reporte"));
        return category;
    }
    
    public ReportedStatus createReportedStatus(ReportedStatus reportedStatus){
        return reportedStatusR.createReportedStatus(reportedStatus);
    }
    
    public ReportedStatus updateReportedStatus(ReportedStatus reportedStatus){
        ReportedStatus reportedStatusDB = reportedStatusR.getById(reportedStatus.getId()).orElseThrow(()-> new CustomException("No se encontró el reporte"));
        if(reportedStatus.getMessageRehabilitation()!= null) {reportedStatusDB.setMessageRehabilitation(reportedStatus.getMessageRehabilitation());}
        if(reportedStatus.getIsAvailable() != null) {reportedStatusDB.setIsAvailable(reportedStatus.getIsAvailable());}
        return reportedStatusR.updateReportedStatus(reportedStatusDB);
    }
    
    public void deleteReportedStatus(Integer id){
        reportedStatusR.getById(id).orElseThrow(()-> new CustomException("No se encontró el reporte"));
        reportedStatusR.deleteReportedStatus(id);
    }
    
}
