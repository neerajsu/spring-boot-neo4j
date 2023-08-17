package dev.farhan.springbootneo4j.repositories;

import dev.farhan.springbootneo4j.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CourseRepository extends Neo4jRepository<Course, Long> {

}
