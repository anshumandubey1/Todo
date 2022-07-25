package com.anshuman.todo.validation.task;

import com.anshuman.todo.exception.InvalidTimeFormatException;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTask {
  @NotEmpty
  @Size(max = 20, message = "Title should not have more than 20 characters")
  private final String title;

  @NotEmpty
  @Size(min = 10, message = "Description should not have less than 10 characters")
  private final String description;

  @NotNull
  @FutureOrPresent
  private final LocalDateTime deadline;

  public AddTask(String title, String description, String time) {
    this.title = title;
    this.description = description;
    try {
      this.deadline = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    } catch (DateTimeParseException e) {
      throw new InvalidTimeFormatException(time);
    }

  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getDeadline() {
    return deadline;
  }

  @Override
  public String toString() {
    return "AddTask{" +
           "title='" + title + '\'' +
           ", description='" + description + '\'' +
           ", deadline=" + deadline +
           '}';
  }
}
