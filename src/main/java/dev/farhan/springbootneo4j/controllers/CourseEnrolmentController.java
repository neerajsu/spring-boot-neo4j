package dev.farhan.springbootneo4j.controllers;

import dev.farhan.springbootneo4j.models.Course;
import dev.farhan.springbootneo4j.objects.CourseDTO;
import dev.farhan.springbootneo4j.objects.CourseEnrolmentDTO;
import dev.farhan.springbootneo4j.queryresults.CourseEnrolmentQueryResult;
import dev.farhan.springbootneo4j.requests.CourseEnrolmentRequest;
import dev.farhan.springbootneo4j.services.CourseService;
import dev.farhan.springbootneo4j.services.LessonService;
import dev.farhan.springbootneo4j.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrolments")
@Slf4j
public class CourseEnrolmentController {

    private final CourseService courseService;
    private final LessonService lessonService;
    private final UserService userService;


    public CourseEnrolmentController(CourseService courseService, LessonService lessonService, UserService userService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<CourseEnrolmentDTO> enrollIn(@RequestBody CourseEnrolmentRequest courseEnrolmentRequest, Principal principal) {

        if (principal == null) {
            log.error("User not authenticated");
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }

        log.info("principal is : " + principal.getName());
        log.info("course identifier is : " + courseEnrolmentRequest.getCourseIdentifier());
        var userOptional = userService.findUserByUsername(principal.getName());

        if (userOptional.isEmpty()) {
            log.error("User is not present in database: " + principal.getName());
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "User does not exist in database");
        }

        var courseEnrolmentDTOBuilder = CourseEnrolmentDTO.builder();

        if (courseService.getEnrolmentStatus(principal.getName(), courseEnrolmentRequest.getCourseIdentifier())) {
            log.info("User " + principal.getName() + " is already enrolled in course with identifier : " + courseEnrolmentRequest.getCourseIdentifier());
            Course course = courseService.getCourseByIdentifier(courseEnrolmentRequest.getCourseIdentifier());
            courseEnrolmentDTOBuilder
                    .course(course)
                    .username(principal.getName())
                    .name(userOptional.get().getName());
        } else {
            CourseEnrolmentQueryResult courseEnrolmentQueryResult = courseService.enrollIn(principal.getName(), courseEnrolmentRequest.getCourseIdentifier());

            courseEnrolmentDTOBuilder
                    .course(courseEnrolmentQueryResult.getCourse())
                    .username(courseEnrolmentQueryResult.getUser().getUsername())
                    .name(courseEnrolmentQueryResult.getUser().getName());
        }
        return new ResponseEntity<>(courseEnrolmentDTOBuilder.build(), HttpStatus.OK);
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
