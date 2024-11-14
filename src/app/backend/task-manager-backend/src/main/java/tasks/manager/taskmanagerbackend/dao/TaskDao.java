package tasks.manager.taskmanagerbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tasks.manager.taskmanagerbackend.bean.Task;

import java.util.List;

public interface TaskDao extends JpaRepository<Task, Integer> {
  List<Task> findByUserId(int userId);
  int deleteAllByUserId(int userId);
}
