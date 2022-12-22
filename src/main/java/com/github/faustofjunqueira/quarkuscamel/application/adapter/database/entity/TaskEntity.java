package com.github.faustofjunqueira.quarkuscamel.application.adapter.database.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Task")
@Where(clause = "deletedAt is null")
@SQLDelete(sql = "UPDATE Task set deletedat = now() where id = ?", check = ResultCheckStyle.COUNT)
@NamedQuery(name="Task.filter", query = "from TaskEntity where ownerId = :ownerId or :ownerId = null")
public class TaskEntity extends PanacheEntityBase {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "ownerid", nullable = false)
    private UUID ownerId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "completedAt")
    private ZonedDateTime completedAt;

    @Column(name = "createdAt", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "deletedAt")
    private ZonedDateTime deletedAt;

    @PreRemove
    public void softDelete() {
        setDeletedAt(ZonedDateTime.now());
    }

    @PrePersist
    public void updateCreatedAt() {
        if (getCreatedAt() == null) {
            setCreatedAt(ZonedDateTime.now());
        }
    }

    public TaskEntity complete() {
        if (getCompletedAt() == null) {
            setCompletedAt(ZonedDateTime.now());
            persist();
        }
        return this;
    }

    public static Optional<TaskEntity> CompleteById(@NonNull UUID taskId) {
        Optional<TaskEntity> taskEntity = findByIdOptional(taskId);
        return taskEntity.map(TaskEntity::complete);
    }

}
