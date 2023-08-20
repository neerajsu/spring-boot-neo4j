package dev.farhan.springbootneo4j.controllers;

import dev.farhan.springbootneo4j.models.Course;
import dev.farhan.springbootneo4j.models.Lesson;
import dev.farhan.springbootneo4j.services.CourseService;
import dev.farhan.springbootneo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    private final LessonService lessonService;

    public CourseController(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> courseIndex() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Course> courseDetails(@PathVariable String identifier) {
        Course course = courseService.getCourseByIdentifier(identifier);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/{identifier}/lessons")
    public ResponseEntity<List<Lesson>> findLessonsByCourseIdentifier(@PathVariable String identifier) {
        Course course = courseService.getCourseByIdentifier(identifier);
        return new ResponseEntity<>(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()), HttpStatus.OK);
    }
}
