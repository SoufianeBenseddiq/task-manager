package tasks.manager.taskmanagerbackend.bean;

import jakarta.persistence.*;

@Entity
public class Task {
  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String title;
  private String description;
  private boolean completed;
  private String dueDate;

  @ManyToOne
  private User user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
