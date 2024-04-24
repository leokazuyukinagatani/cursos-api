package com.devkazu.cursosapi.modules.course.repositories;

import com.devkazu.cursosapi.modules.course.CourseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
  Optional<CourseEntity> findByName(String name);
  Optional<List<CourseEntity>> findByCategory(String category);
  Optional<CourseEntity> findByNameAndCategory(String name, String category);
}
