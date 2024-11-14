package tasks.manager.taskmanagerbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.manager.taskmanagerbackend.bean.User;
import tasks.manager.taskmanagerbackend.dao.UserDao;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  // Get all users
  public List<User> getAllUsers() {
    return userDao.findAll();
  }

  // Get a user by ID
  public Optional<User> getUserById(int id) {
    return Optional.ofNullable(userDao.findById(id).orElse(null));
  }

  // Create a new user
  public User createUser(User user) {
    return userDao.save(user);
  }

  // Update an existing user
  public User updateUser(User user) {
    return userDao.save(user);
  }

  // Delete a user by ID
  public void deleteUser(int id) {
    userDao.deleteById(id);
  }
}
