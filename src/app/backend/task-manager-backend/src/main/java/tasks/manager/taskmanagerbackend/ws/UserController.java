package tasks.manager.taskmanagerbackend.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasks.manager.taskmanagerbackend.bean.User;
import tasks.manager.taskmanagerbackend.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  // Get all users
  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  // Get user by ID
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable int userId) {
    Optional<User> user = userService.getUserById(userId); // Get user as Optional
    return user.isPresent() // Check if the user exists
      ? new ResponseEntity<>(user.get(), HttpStatus.OK) // Return user with status 200 OK
      : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if user not found
  }


  // Create a new user
  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // 201 for successful creation
  }

  // Update an existing user
  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
    Optional<User> existingUser = userService.getUserById(userId);
    if (existingUser.isPresent()) { // Check if the user exists
      user.setId(userId); // Ensure the ID is set to the one in the path variable
      User updatedUser = userService.updateUser(user); // Update the user
      return new ResponseEntity<>(updatedUser, HttpStatus.OK); // Return the updated user with 200 OK
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if user not found
    }
  }

  // Delete a user by ID
  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 for successful deletion
  }
}
