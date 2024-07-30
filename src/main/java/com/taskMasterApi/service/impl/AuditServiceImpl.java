package com.taskMasterApi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskMasterApi.domain.model.Audit;
import com.taskMasterApi.repository.AuditRepository;

@Service
public class AuditServiceImpl {

    @Autowired
    private AuditRepository auditRepository;

    public void logAction(String entity, String action, String username) {
        Audit audit = new Audit();
        audit.setEntity(entity);
        audit.setAction(action);
        audit.setUsername(username);
        auditRepository.save(audit);
    }
}

