package com.devkazu.cursosapi.modules.course.useCases;

import com.devkazu.cursosapi.exceptions.CourseNotFoundException;

import com.devkazu.cursosapi.modules.course.repositories.CourseRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public void execute(UUID id) {
    var courseFound = this.courseRepository.findById(id);

    var course = courseFound.orElseThrow(() ->
      new CourseNotFoundException("Course not found")
    );

    this.courseRepository.delete(course);
  }
}
