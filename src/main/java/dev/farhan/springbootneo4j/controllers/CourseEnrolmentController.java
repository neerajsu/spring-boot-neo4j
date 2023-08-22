package dev.farhan.springbootneo4j.controllers;

import dev.farhan.springbootneo4j.models.Course;
import dev.farhan.springbootneo4j.objects.CourseDTO;
import dev.farhan.springbootneo4j.objects.CourseEnrolmentDTO;
import dev.farhan.springbootneo4j.queryresults.CourseEnrolmentQueryResult;
import dev.farhan.springbootneo4j.requests.CourseEnrolmentRequest;
import dev.farhan.springbootneo4j.services.CourseService;
import dev.farhan.springbootneo4j.services.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrolments")
@Slf4j
public class CourseEnrolmentController {

    private final CourseService courseService;
    private final LessonService lessonService;


    public CourseEnrolmentController(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @PostMapping("/")
    public ResponseEntity<CourseEnrolmentDTO> enrollIn(@RequestBody CourseEnrolmentRequest courseEnrolmentRequest, Principal principal) {
        CourseEnrolmentQueryResult courseEnrolmentQueryResult = courseService.enrollIn(principal.getName(), courseEnrolmentRequest.getCourseIdentifier());
        log.info("principal is : " + principal.getName());
        log.info("course identifier is : " + courseEnrolmentRequest.getCourseIdentifier());
        var courseEnrolmentDTO = CourseEnrolmentDTO.builder()
                .course(courseEnrolmentQueryResult.getCourse())
                .username(courseEnrolmentQueryResult.getUser().getUsername())
                .name(courseEnrolmentQueryResult.getUser().getName())
                .build();

        return new ResponseEntity<>(courseEnrolmentDTO, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> enrolments(Principal principal) {
        List<Course> course = courseService.getAllEnrolledCoursesByUsername(principal.getName());
        List<CourseDTO> courseDTOs = course.stream()
                .map(c -> CourseDTO.builder()
                        .title(c.getTitle())
                        .identifier(c.getIdentifier())
                        .teacher(c.getTeacher())
                        .isEnrolled(true)
                        .lessons(lessonService.getAllLessonsByCourseIdentifier(c.getIdentifier()))
                        .build())
                .toList();
        return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
    }
}
