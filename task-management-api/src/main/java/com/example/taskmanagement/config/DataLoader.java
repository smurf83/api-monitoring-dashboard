package com.example.taskmanagement.config;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    
    private final TaskRepository taskRepository;
    
    @Override
    public void run(String... args) throws Exception {
        loadSampleData();
    }
    
    private void loadSampleData() {
        if (taskRepository.count() == 0) {
            log.info("Loading sample data...");
            
            List<Task> sampleTasks = Arrays.asList(
                    createTask("Complete project documentation", 
                            "Write comprehensive documentation for the task management API",
                            Task.TaskStatus.IN_PROGRESS, 
                            Task.TaskPriority.HIGH,
                            LocalDateTime.now().plusDays(3)),
                    
                    createTask("Review pull requests", 
                            "Review and merge pending pull requests from team members",
                            Task.TaskStatus.PENDING, 
                            Task.TaskPriority.MEDIUM,
                            LocalDateTime.now().plusDays(1)),
                    
                    createTask("Database optimization", 
                            "Optimize database queries and add necessary indexes",
                            Task.TaskStatus.PENDING, 
                            Task.TaskPriority.LOW,
                            LocalDateTime.now().plusDays(7)),
                    
                    createTask("Security audit", 
                            "Conduct security audit of the application",
                            Task.TaskStatus.PENDING, 
                            Task.TaskPriority.URGENT,
                            LocalDateTime.now().plusDays(2)),
                    
                    createTask("Unit tests", 
                            "Write comprehensive unit tests for all service methods",
                            Task.TaskStatus.COMPLETED, 
                            Task.TaskPriority.HIGH,
                            LocalDateTime.now().minusDays(1)),
                    
                    createTask("API integration", 
                            "Integrate with external payment API",
                            Task.TaskStatus.CANCELLED, 
                            Task.TaskPriority.MEDIUM,
                            LocalDateTime.now().minusDays(5)),
                    
                    createTask("Performance testing", 
                            "Conduct load testing and performance optimization",
                            Task.TaskStatus.IN_PROGRESS, 
                            Task.TaskPriority.MEDIUM,
                            LocalDateTime.now().plusDays(10)),
                    
                    createTask("Code refactoring", 
                            "Refactor legacy code to improve maintainability",
                            Task.TaskStatus.PENDING, 
                            Task.TaskPriority.LOW,
                            LocalDateTime.now().plusDays(14)),
                    
                    createTask("Bug fixes", 
                            "Fix critical bugs reported by QA team",
                            Task.TaskStatus.IN_PROGRESS, 
                            Task.TaskPriority.URGENT,
                            LocalDateTime.now().plusHours(6)),
                    
                    createTask("User manual", 
                            "Create user manual for the application",
                            Task.TaskStatus.PENDING, 
                            Task.TaskPriority.LOW,
                            LocalDateTime.now().plusDays(30))
            );
            
            taskRepository.saveAll(sampleTasks);
            log.info("Sample data loaded successfully! Created {} tasks", sampleTasks.size());
        } else {
            log.info("Sample data already exists. Skipping data loading.");
        }
    }
    
    private Task createTask(String title, String description, Task.TaskStatus status, 
                           Task.TaskPriority priority, LocalDateTime dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        return task;
    }
}