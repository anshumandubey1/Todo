package com.anshuman.todo.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ValidationException extends ResponseStatusException {
  public ValidationException(List<ObjectError> allErrors) {
    super(
        HttpStatus.BAD_REQUEST,
        String.join(", ", allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList())
    );
  }

  public ValidationException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}
