package tasks.manager.taskmanagerbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tasks.manager.taskmanagerbackend.bean.User;

public interface UserDao extends JpaRepository<User, Integer> {
  // Removed redundant findById method, JpaRepository already provides it
}
