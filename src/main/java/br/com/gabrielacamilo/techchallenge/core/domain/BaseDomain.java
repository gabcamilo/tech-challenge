package br.com.gabrielacamilo.techchallenge.core.domain;

import java.time.LocalDateTime;

public abstract class BaseDomain {
    private final String id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseDomain() {
        this.id = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public BaseDomain(String id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(){
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getDomainName() {
        return this.getClass().getSimpleName();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
