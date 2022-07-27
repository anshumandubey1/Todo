package com.anshuman.todo.service;

import com.anshuman.todo.Constants;
import com.anshuman.todo.exception.InvalidIdException;
import com.anshuman.todo.exception.UnauthorizedAccessException;
import com.anshuman.todo.model.Task;
import com.anshuman.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

  @Autowired
  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {this.taskRepository = taskRepository;}

  private void isAuthorized(Task task) {
    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    if(!task.getUserId().equals(userId))
      throw new UnauthorizedAccessException();
  }

  public Page<Task> findAll(String userId, int page) {
    Pageable pageable = Pageable.ofSize(Constants.PAGE_SIZE).withPage(page);
    return taskRepository.findByUserId(userId, pageable).orElseThrow();
  }

  public Task findById(String id) {
    Task task = taskRepository.findById(id).orElseThrow(InvalidIdException::new);
    isAuthorized(task);
    return task;
  }

  public Task addTask(String userId, String title, String description, LocalDateTime time) {
    return taskRepository.insert(new Task(userId, title, description, time));
  }

  public Task deleteById(String id) {
    Task task = findById(id);
    taskRepository.deleteById(id);
    return task;
  }

  public Task updateTask(String id, String title, String description, LocalDateTime deadline) {
    Task task = findById(id);
    if (title != null) task.setTitle(title);
    if (description != null) task.setDescription(description);
    if (deadline != null) task.setDeadline(deadline);
    return taskRepository.save(task);
  }

  public Task toggleDone(String id) {
    Task task = findById(id);
    task.toggleDone();
    return taskRepository.save(task);
  }
}
