package com.taskMasterApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskMasterApi.domain.model.Audit;
import com.taskMasterApi.repository.AuditRepository;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    
    private AuditRepository auditRepository;

    @Autowired
    public AuditController(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
    @GetMapping
    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }
}