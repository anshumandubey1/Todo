package com.anshuman.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedAccessException extends ResponseStatusException {

  public UnauthorizedAccessException() {
    super(HttpStatus.UNAUTHORIZED, "This User doesn't have access to this data");
  }
}
