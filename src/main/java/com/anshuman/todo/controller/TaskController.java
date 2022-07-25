package com.anshuman.todo.controller;

import com.anshuman.todo.exception.ValidationException;
import com.anshuman.todo.model.Task;
import com.anshuman.todo.service.TaskService;
import com.anshuman.todo.validation.task.AddTask;
import com.anshuman.todo.validation.task.UpdateTask;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TaskController {

  public static final String USERNAME = new ObjectId().toString();

  private final Logger logger = LoggerFactory.getLogger(TaskController.class);
  TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/")
  public ResponseEntity<Page<Task>> listAllTasks(
      @RequestParam(required = true)
          int page
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll(getUserId(), page));
  }

  private String getUserId() {
//    logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
//    logger.info(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
//    logger.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//    return SecurityContextHolder.getContext().getAuthentication().getName();
    return USERNAME;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> getTask(
      @PathVariable("id")
          String id
  ) {
    Task task = taskService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @PostMapping("/")
  public ResponseEntity<Task> addTask(
      @Valid
      @RequestBody
          AddTask task, BindingResult result
  ) {
    if (result.hasErrors()) {
      throw new ValidationException(result.getAllErrors());
    }
    String request;
    request = task.toString();
    logger.debug(request);
    Task newTask = taskService.addTask(getUserId(), task.getTitle(), task.getDescription(), task.getDeadline());
    URI location =
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(newTask.getId()).toUri();
    return ResponseEntity.status(HttpStatus.CREATED).location(location).body(newTask);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Task> toggleDone(
      @PathVariable("id")
          String id
  ) {
    Task updatedTask = taskService.toggleDone(id);
    return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Task> deleteTask(
      @PathVariable("id")
          String id
  ) {
    Task task = taskService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Task> updateTask(
      @PathVariable("id")
          String id,
      @Valid
      @RequestBody
          UpdateTask task,
      BindingResult result
  ) {
    if (result.hasErrors()) {
      throw new ValidationException(result.getAllErrors());
    }
    String request;
    request = task.toString();
    logger.debug(request);
    Task updatedTask =
        taskService.updateTask(id, task.getTitle(), task.getDescription(), task.getDeadline());
    return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
  }
}
