package tasks.manager.taskmanagerbackend.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.manager.taskmanagerbackend.bean.Task;
import tasks.manager.taskmanagerbackend.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  // Get all tasks
  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  // Get tasks by user ID
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable int userId) {
    List<Task> tasks = taskService.getTasksByUserId(userId);
    if (tasks.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 if no tasks found
    }
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }

  // Get task by ID
  @GetMapping("/{taskId}")
  public ResponseEntity<Task> getTaskById(@PathVariable int taskId) {
    Optional<Task> task = taskService.getTaskById(taskId);
    return task.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 if task not found
  }

  // Create a new task
  @PostMapping
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task createdTask = taskService.createTask(task);
    return new ResponseEntity<>(createdTask, HttpStatus.CREATED); // 201 for successful creation
  }

  // Update an existing task
  @PutMapping("/{taskId}")
  public ResponseEntity<Task> updateTask(@PathVariable int taskId, @RequestBody Task task) {
    Optional<Task> existingTask = taskService.getTaskById(taskId);
    if (existingTask.isPresent()) {
      task.setId(taskId);
      Task updatedTask = taskService.updateTask(task);
      return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if task not found
    }
  }

  // Delete a task by ID
  @DeleteMapping("/{taskId}")
  public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
    if (taskService.deleteTask(taskId)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 for successful deletion
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if task not found
  }

  // Mark task as completed
  @PutMapping("/{taskId}/complete")
  public ResponseEntity<Task> markTaskAsCompleted(@PathVariable int taskId) {
    Task updatedTask = taskService.markTaskAsCompleted(taskId);
    if (updatedTask != null) {
      return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if task not found
  }
}
