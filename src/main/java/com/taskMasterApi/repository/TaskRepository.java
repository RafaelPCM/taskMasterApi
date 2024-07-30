package com.taskMasterApi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taskMasterApi.domain.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.id = :taskId")
    Optional<Task> findByTaskId(@Param("taskId") Long taskId);

    Page<Task> findAllByCompleted(Boolean completed, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.title = :title")
    Optional<Task> findByTitle(String title);
}