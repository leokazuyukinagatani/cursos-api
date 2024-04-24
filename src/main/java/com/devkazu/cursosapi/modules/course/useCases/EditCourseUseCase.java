package com.devkazu.cursosapi.modules.course.useCases;

import com.devkazu.cursosapi.exceptions.CourseNotFoundException;
import com.devkazu.cursosapi.modules.course.CourseEntity;
import com.devkazu.cursosapi.modules.course.dto.CourseCreateResponseDTO;
import com.devkazu.cursosapi.modules.course.repositories.CourseRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public CourseCreateResponseDTO execute(CourseEntity course, UUID id) {
    var optionalCourse = this.courseRepository.findById(id);

    if (optionalCourse.isEmpty()) {
      throw new CourseNotFoundException("Course not found");
    }
    // Atualiza apenas as propriedades que foram alteradas
    CourseEntity existingCourse = optionalCourse.get();
    if (course.getName() != null) {
      existingCourse.setName(course.getName());
    }

    if (course.getCategory() != null) {
      existingCourse.setCategory(course.getCategory());
    }

    var savedCourse = this.courseRepository.save(existingCourse);

    return CourseCreateResponseDTO
      .builder()
      .category(savedCourse.getCategory())
      .name(savedCourse.getName())
      .active(savedCourse.isActive())
      .build();
  }
}
