package dev.farhan.springbootneo4j.services;

import dev.farhan.springbootneo4j.models.Course;
import dev.farhan.springbootneo4j.repositories.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
