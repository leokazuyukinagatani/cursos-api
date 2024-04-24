package com.devkazu.cursosapi.modules.course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity(name = "course")
public class CourseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  String name;
  String category;
  boolean active;

  @CreationTimestamp
  Instant created_at;

  @UpdateTimestamp
  Instant updated_at;
}
