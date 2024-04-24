package com.devkazu.cursosapi.modules.course.useCases;

import com.devkazu.cursosapi.exceptions.CourseFoundException;
import com.devkazu.cursosapi.modules.course.CourseEntity;
import com.devkazu.cursosapi.modules.course.dto.CourseCreateResponseDTO;
import com.devkazu.cursosapi.modules.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public CourseCreateResponseDTO execute(CourseEntity course) {
    this.courseRepository.findByName(course.getName())
      .ifPresent(c -> {
        throw new CourseFoundException("Course already exists");
      });

    var savedCourse = this.courseRepository.save(course);

    return CourseCreateResponseDTO
      .builder()
      .category(savedCourse.getCategory())
      .name(savedCourse.getName())
      .active(savedCourse.isActive())
      .build();
  }
}
