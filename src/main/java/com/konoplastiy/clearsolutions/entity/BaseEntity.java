package com.konoplastiy.clearsolutions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * This class serves as the base entity for all entities in the system.
 * It is annotated with @MappedSuperclass to indicate that it will not be mapped to a table,
 * but its attributes will be inherited by subclasses.
 *
 * <p>The BaseEntity includes auditing fields such as createdAt, createdBy, updatedAt, and updatedBy,
 * which are automatically populated by JPA Auditing.
 *
 * <p>These auditing fields track the creation and modification timestamps as well as the user
 * responsible for creating or updating the entity.
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}