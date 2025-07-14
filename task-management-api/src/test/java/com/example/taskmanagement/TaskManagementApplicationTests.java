package com.example.taskmanagement;

import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TaskManagementApplicationTests {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void contextLoads() {
        // Test that the application context loads successfully
        assertThat(taskService).isNotNull();
        assertThat(taskRepository).isNotNull();
    }

    @Test
    void testCreateTask() {
        // Test creating a new task
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setPriority(Task.Priority.HIGH);
        task.setStatus(Task.Status.TODO);
        task.setDueDate(LocalDateTime.now().plusDays(1));

        Task savedTask = taskService.createTask(task);

        assertThat(savedTask.getId()).isNotNull();
        assertThat(savedTask.getTitle()).isEqualTo("Test Task");
        assertThat(savedTask.getDescription()).isEqualTo("This is a test task");
        assertThat(savedTask.getPriority()).isEqualTo(Task.Priority.HIGH);
        assertThat(savedTask.getStatus()).isEqualTo(Task.Status.TODO);
        assertThat(savedTask.getCreatedAt()).isNotNull();
        assertThat(savedTask.getUpdatedAt()).isNotNull();
    }

    @Test
    void testGetAllTasks() {
        // Test retrieving all tasks
        List<Task> tasks = taskService.getAllTasks();
        assertThat(tasks).isNotEmpty();
    }

    @Test
    void testGetTaskById() {
        // Create a task first
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setPriority(Task.Priority.MEDIUM);
        task.setStatus(Task.Status.TODO);

        Task savedTask = taskService.createTask(task);

        // Test getting the task by ID
        Optional<Task> retrievedTask = taskService.getTaskById(savedTask.getId());
        assertThat(retrievedTask).isPresent();
        assertThat(retrievedTask.get().getTitle()).isEqualTo("Test Task");
    }

    @Test
    void testUpdateTask() {
        // Create a task first
        Task task = new Task();
        task.setTitle("Original Task");
        task.setDescription("Original description");
        task.setPriority(Task.Priority.LOW);
        task.setStatus(Task.Status.TODO);

        Task savedTask = taskService.createTask(task);

        // Update the task
        savedTask.setTitle("Updated Task");
        savedTask.setDescription("Updated description");
        savedTask.setPriority(Task.Priority.HIGH);
        savedTask.setStatus(Task.Status.IN_PROGRESS);

        Task updatedTask = taskService.updateTask(savedTask.getId(), savedTask);

        assertThat(updatedTask.getTitle()).isEqualTo("Updated Task");
        assertThat(updatedTask.getDescription()).isEqualTo("Updated description");
        assertThat(updatedTask.getPriority()).isEqualTo(Task.Priority.HIGH);
        assertThat(updatedTask.getStatus()).isEqualTo(Task.Status.IN_PROGRESS);
    }

    @Test
    void testDeleteTask() {
        // Create a task first
        Task task = new Task();
        task.setTitle("Task to Delete");
        task.setDescription("This task will be deleted");
        task.setPriority(Task.Priority.LOW);
        task.setStatus(Task.Status.TODO);

        Task savedTask = taskService.createTask(task);
        Long taskId = savedTask.getId();

        // Delete the task
        taskService.deleteTask(taskId);

        // Verify the task is deleted
        Optional<Task> deletedTask = taskService.getTaskById(taskId);
        assertThat(deletedTask).isEmpty();
    }

    @Test
    void testGetTasksByStatus() {
        // Test getting tasks by status
        List<Task> todoTasks = taskService.getTasksByStatus(Task.Status.TODO);
        assertThat(todoTasks).isNotEmpty();
        assertThat(todoTasks).allMatch(task -> task.getStatus() == Task.Status.TODO);
    }

    @Test
    void testGetTasksByPriority() {
        // Test getting tasks by priority
        List<Task> highPriorityTasks = taskService.getTasksByPriority(Task.Priority.HIGH);
        assertThat(highPriorityTasks).isNotEmpty();
        assertThat(highPriorityTasks).allMatch(task -> task.getPriority() == Task.Priority.HIGH);
    }

    @Test
    void testSearchTasksByTitle() {
        // Test searching tasks by title
        List<Task> searchResults = taskService.searchTasksByTitle("documentation");
        assertThat(searchResults).isNotEmpty();
        assertThat(searchResults).allMatch(task -> 
            task.getTitle().toLowerCase().contains("documentation"));
    }

    @Test
    void testMarkTaskAsCompleted() {
        // Create a task first
        Task task = new Task();
        task.setTitle("Task to Complete");
        task.setDescription("This task will be marked as completed");
        task.setPriority(Task.Priority.MEDIUM);
        task.setStatus(Task.Status.TODO);

        Task savedTask = taskService.createTask(task);

        // Mark the task as completed
        Task completedTask = taskService.markTaskAsCompleted(savedTask.getId());

        assertThat(completedTask.getStatus()).isEqualTo(Task.Status.COMPLETED);
    }
}