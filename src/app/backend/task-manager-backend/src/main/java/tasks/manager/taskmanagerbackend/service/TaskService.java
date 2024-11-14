package tasks.manager.taskmanagerbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.manager.taskmanagerbackend.bean.Task;
import tasks.manager.taskmanagerbackend.bean.User;
import tasks.manager.taskmanagerbackend.dao.TaskDao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  @Autowired
  private TaskDao taskDao;

  @Autowired
  private UserService userService;

  // Get all tasks
  public List<Task> getAllTasks() {
    return taskDao.findAll();
  }

  // Get tasks by user ID
  public List<Task> getTasksByUserId(int userId) {
    Optional<User> user = userService.getUserById(userId);
    if (user.isEmpty()) {  // Check if user is not present
      return Collections.emptyList();  // Return an empty list if user not found
    }
    return taskDao.findByUserId(userId);  // Return the tasks for the user if found
  }


  // Get a task by ID
  public Optional<Task> getTaskById(int id) {
    return taskDao.findById(id);
  }

  // Create a new task
  public Task createTask(Task task) {
    return taskDao.save(task);
  }

  // Update an existing task
  public Task updateTask(Task task) {
    return taskDao.save(task);
  }

  // Delete a task by ID
  public boolean deleteTask(int taskId) {
    Optional<Task> task = taskDao.findById(taskId);
    if (task.isPresent()) {
      taskDao.delete(task.get());
      return true;  // Task deleted successfully
    }
    return false;  // Task not found
  }


  // Delete all tasks by user ID
  public int deleteAllByUserId(int userId) {
    return taskDao.deleteAllByUserId(userId);
  }

  // Mark task as completed
  public Task markTaskAsCompleted(int taskId) {
    Optional<Task> task = taskDao.findById(taskId);
    if (task.isPresent()) {
      Task updatedTask = task.get();
      updatedTask.setCompleted(true);
      return taskDao.save(updatedTask);
    }
    return null;
  }
}
