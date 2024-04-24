package com.devkazu.cursosapi.modules.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCreateResponseDTO {

  String name;
  String category;
  boolean active;
}
