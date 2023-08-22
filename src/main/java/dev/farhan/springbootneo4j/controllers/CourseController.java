package dev.farhan.springbootneo4j.controllers;

import dev.farhan.springbootneo4j.models.Course;
import dev.farhan.springbootneo4j.models.Lesson;
import dev.farhan.springbootneo4j.objects.CourseDTO;
import dev.farhan.springbootneo4j.services.CourseService;
import dev.farhan.springbootneo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<CourseDTO>> courseIndex(Principal principal) {
        List<Course> courses = courseService.getAllCourses();

        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO();

                    responseCourse.setIdentifier(course.getIdentifier());
                    responseCourse.setTitle(course.getTitle());
                    responseCourse.setTeacher(course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));

                    if (principal != null)
                        responseCourse.setEnrolled(courseService.getEnrolmentStatus(principal.getName(),
                                course.getIdentifier()));

                    return responseCourse;
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CourseDTO> courseDetails(@PathVariable String identifier, Principal principal) {
        Course course = courseService.getCourseByIdentifier(identifier);
        CourseDTO responseCourse = new CourseDTO();

        responseCourse.setIdentifier(course.getIdentifier());
        responseCourse.setTitle(course.getTitle());
        responseCourse.setTeacher(course.getTeacher());
        responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(identifier));

        if (principal != null)
            responseCourse.setEnrolled(courseService.getEnrolmentStatus(principal.getName(), identifier));

        return new ResponseEntity<>(responseCourse, HttpStatus.OK);
    }

    @GetMapping("/{identifier}/lessons")
    public ResponseEntity<List<Lesson>> findLessonsByCourseIdentifier(@PathVariable String identifier) {
        Course course = courseService.getCourseByIdentifier(identifier);
        return new ResponseEntity<>(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()), HttpStatus.OK);
    }
}
