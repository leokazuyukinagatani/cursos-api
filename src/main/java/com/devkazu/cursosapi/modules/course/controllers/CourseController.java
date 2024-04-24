package com.devkazu.cursosapi.modules.course.controllers;

import com.devkazu.cursosapi.modules.course.CourseEntity;
import com.devkazu.cursosapi.modules.course.useCases.CreateCourseUseCase;
import com.devkazu.cursosapi.modules.course.useCases.DeleteCourseUseCase;
import com.devkazu.cursosapi.modules.course.useCases.EditCourseStatusUseCase;
import com.devkazu.cursosapi.modules.course.useCases.EditCourseUseCase;
import com.devkazu.cursosapi.modules.course.useCases.SearchCourseUseCase;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

  @Autowired
  private CreateCourseUseCase createCourseUseCase;

  @Autowired
  private DeleteCourseUseCase deleteCourseUseCase;

  @Autowired
  private EditCourseUseCase editCourseUseCase;

  @Autowired
  private EditCourseStatusUseCase editCourseStatusUseCase;

  @Autowired
  private SearchCourseUseCase searchCourseUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(
    @Valid @RequestBody CourseEntity entity
  ) {
    try {
      var result = this.createCourseUseCase.execute(entity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) {
    try {
      this.deleteCourseUseCase.execute(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> edit(
    @Valid @RequestBody CourseEntity entity,
    @PathVariable UUID id
  ) {
    try {
      var response = this.editCourseUseCase.execute(entity, id);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/{id}/active")
  public ResponseEntity<Object> editStatus(@PathVariable UUID id) {
    try {
      var response = this.editCourseStatusUseCase.execute(id);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  public ResponseEntity<Object> get(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String category
  ) {
    try {
      var response = this.searchCourseUseCase.execute(name, category);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
