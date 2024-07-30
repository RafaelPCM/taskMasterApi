package com.taskMasterApi.service;

public interface AuditService {

    void logAction(String entity, String action, String username);
}

