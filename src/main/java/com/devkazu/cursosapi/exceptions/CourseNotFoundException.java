package com.devkazu.cursosapi.exceptions;

public class CourseNotFoundException extends RuntimeException {

  public CourseNotFoundException(String message) {
    super(message);
  }
}
