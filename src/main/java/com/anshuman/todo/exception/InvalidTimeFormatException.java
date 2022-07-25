package com.anshuman.todo.exception;

public class InvalidTimeFormatException extends ValidationException {
  public InvalidTimeFormatException(String time) {
    super(time + " is not a valid ISO Date-Time Format");
  }
}
