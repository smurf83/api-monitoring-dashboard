package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskService {
    
    private final TaskRepository taskRepository;
    
    public List<Task> getAllTasks() {
        log.info("Fetching all tasks");
        return taskRepository.findAll();
    }
    
    public Page<Task> getAllTasksPaginated(Pageable pageable) {
        log.info("Fetching tasks with pagination: {}", pageable);
        return taskRepository.findAll(pageable);
    }
    
    public Optional<Task> getTaskById(Long id) {
        log.info("Fetching task with id: {}", id);
        return taskRepository.findById(id);
    }
    
    public Task createTask(Task task) {
        log.info("Creating new task: {}", task.getTitle());
        return taskRepository.save(task);
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        log.info("Updating task with id: {}", id);
        
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        
        return taskRepository.save(task);
    }
    
    public void deleteTask(Long id) {
        log.info("Deleting task with id: {}", id);
        
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        
        taskRepository.deleteById(id);
    }
    
    public List<Task> getTasksByStatus(Task.TaskStatus status) {
        log.info("Fetching tasks with status: {}", status);
        return taskRepository.findByStatus(status);
    }
    
    public List<Task> getTasksByPriority(Task.TaskPriority priority) {
        log.info("Fetching tasks with priority: {}", priority);
        return taskRepository.findByPriority(priority);
    }
    
    public List<Task> searchTasksByTitle(String title) {
        log.info("Searching tasks with title containing: {}", title);
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Task> getOverdueTasks() {
        log.info("Fetching overdue tasks");
        return taskRepository.findOverdueTasks(LocalDateTime.now());
    }
    
    public List<Task> getTasksDueBetween(LocalDateTime start, LocalDateTime end) {
        log.info("Fetching tasks due between {} and {}", start, end);
        return taskRepository.findByDueDateBetween(start, end);
    }
    
    public Task updateTaskStatus(Long id, Task.TaskStatus status) {
        log.info("Updating task status for id: {} to {}", id, status);
        
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        
        task.setStatus(status);
        return taskRepository.save(task);
    }
    
    public long getTaskCountByStatus(Task.TaskStatus status) {
        log.info("Getting task count for status: {}", status);
        return taskRepository.countByStatus(status);
    }
    
    public static class TaskNotFoundException extends RuntimeException {
        public TaskNotFoundException(String message) {
            super(message);
        }
    }
}