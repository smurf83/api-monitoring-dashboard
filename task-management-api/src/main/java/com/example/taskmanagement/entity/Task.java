package com.example.taskmanagement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Task entity for task management")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Schema(description = "Title of the task", example = "Complete project documentation")
    private String title;

    @Column(length = 500)
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Schema(description = "Description of the task", example = "Write comprehensive documentation for the new API endpoints")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Priority level of the task", example = "HIGH")
    private Priority priority = Priority.MEDIUM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Current status of the task", example = "IN_PROGRESS")
    private Status status = Status.TODO;

    @Column(name = "due_date")
    @Schema(description = "Due date for the task", example = "2024-01-15T10:30:00")
    private LocalDateTime dueDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Timestamp when the task was created")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Timestamp when the task was last updated")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Priority {
        LOW, MEDIUM, HIGH, URGENT
    }

    public enum Status {
        TODO, IN_PROGRESS, COMPLETED, CANCELLED
    }
}