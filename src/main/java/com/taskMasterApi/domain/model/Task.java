package com.taskMasterApi.domain.model;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import com.taskMasterApi.domain.enums.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "title", columnDefinition = "text", length = 100)
    private String title;

    @Column(name = "description", columnDefinition = "text", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusEnum")
    private StatusEnum statusEnum = StatusEnum.TODO;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    // @ManyToOne
	// @JoinColumn(name = "usuario_criador")
	// private User usuarioCriador;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
