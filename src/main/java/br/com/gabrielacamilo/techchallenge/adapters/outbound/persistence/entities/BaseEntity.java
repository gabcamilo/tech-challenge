package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public abstract class BaseEntity {

    @Id
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public BaseEntity(String id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public abstract <D> D toDomain();

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Deprecated // Spring eyes only
    public BaseEntity() {
    }
}
