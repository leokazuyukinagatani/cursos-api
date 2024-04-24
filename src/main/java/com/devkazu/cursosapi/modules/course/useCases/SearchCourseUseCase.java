package com.devkazu.cursosapi.modules.course.useCases;

import com.devkazu.cursosapi.modules.course.CourseEntity;
import com.devkazu.cursosapi.modules.course.repositories.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchCourseUseCase {

  @Autowired
  CourseRepository courseRepository;

  public Optional<List<CourseEntity>> execute(String name, String category) {
    courseRepository.findByNameAndCategory(name, category);
    courseRepository.findByCategory(category);
    courseRepository.findByName(name);

    if (
      name != null && !name.isEmpty() && category != null && !category.isEmpty()
    ) {
      // Se ambos os parâmetros não forem nulos, busca por nome e categoria
      return courseRepository
        .findByNameAndCategory(name, category)
        .map(List::of);
    } else if (category != null && !category.isEmpty()) {
      // Se apenas a categoria não for nula, busca por categoria
      return courseRepository.findByCategory(category);
    } else if (name != null && !name.isEmpty()) {
      // Se apenas o nome não for nulo, busca por nome
      return courseRepository.findByName(name).map(List::of);
    } else {
      // Se nenhum parâmetro for fornecido, retorna uma lista vazia
      return Optional.empty();
    }
  }
}
