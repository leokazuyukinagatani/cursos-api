package com.devkazu.cursosapi.modules.course.useCases;

import com.devkazu.cursosapi.exceptions.CourseNotFoundException;
import com.devkazu.cursosapi.modules.course.dto.CourseCreateResponseDTO;
import com.devkazu.cursosapi.modules.course.repositories.CourseRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditCourseStatusUseCase {

  @Autowired
  CourseRepository courseRepository;

  public CourseCreateResponseDTO execute(UUID id) {
    var optionalCourse = this.courseRepository.findById(id);

    var existingCourse = optionalCourse.orElseThrow(() ->
      new CourseNotFoundException("Course not found")
    );

    existingCourse.setActive(!existingCourse.isActive());

    var savedCourse = this.courseRepository.save(existingCourse);

    return CourseCreateResponseDTO
      .builder()
      .category(savedCourse.getCategory())
      .name(savedCourse.getName())
      .active(savedCourse.isActive())
      .build();
  }
}
