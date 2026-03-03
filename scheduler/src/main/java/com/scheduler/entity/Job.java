package com.scheduler.entity;

import com.scheduler.enums.JobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "jobs",
        indexes = {
                @Index(name = "idx_jobs_sched", columnList = "status, next_run_time")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    private UUID id;

    @Column(name = "task_type", length = 100)
    private String taskType;

    @Column(name = "cron_expr", length = 50)
    private String cronExpr;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private JobStatus status;

    private Integer priority;

    @Column(columnDefinition = "jsonb")
    private String payload;

    @Column(name = "next_run_time")
    private LocalDateTime nextRunTime;

    @Column(name = "retry_count")
    private Integer retryCount = 0;

    @Column(name = "max_retries")
    private Integer maxRetries = 3;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
