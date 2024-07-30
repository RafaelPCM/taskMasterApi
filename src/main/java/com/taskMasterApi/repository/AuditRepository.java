package com.taskMasterApi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskMasterApi.domain.model.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
}
