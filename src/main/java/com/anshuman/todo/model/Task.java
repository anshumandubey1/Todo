package com.anshuman.todo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

@Document("tasks")
public class Task {

  @Id
  private String id;

  @NotNull
  @Indexed
  private String userId;
  @NotNull
  private String title;
  @NotNull
  private String description;
  @NotNull
  private LocalDateTime deadline;
  private LocalDateTime doneAt;

  @CreatedDate
  private Instant createdAt;

  @LastModifiedDate
  private Instant updatedAt;

  public Task() {
  }

  public Task(String userId, String title, String description, LocalDateTime deadline) {
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.deadline = deadline;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public LocalDateTime getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }

  public LocalDateTime getDoneAt() {
    return doneAt;
  }

  public void setDoneAt(LocalDateTime doneAt) {
    this.doneAt = doneAt;
  }

  public void toggleDone() {
    if(doneAt!= null) {
      doneAt = null;
    } else {
      doneAt = LocalDateTime.now();
    }
  }
}
