package dev.farhan.springbootneo4j.services;

import dev.farhan.springbootneo4j.models.Course;
import dev.farhan.springbootneo4j.queryresults.CourseEnrolmentQueryResult;
import dev.farhan.springbootneo4j.repositories.CourseRepository;
import dev.farhan.springbootneo4j.repositories.UserRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseByIdentifier(String identifier) {
        return courseRepository.findCourseByIdentifier(identifier)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public boolean getEnrolmentStatus(String username, String courseIdentifier) {
        return userRepository.findEnrolmentStatus(username, courseIdentifier);
    }

    public CourseEnrolmentQueryResult enrollIn(String username, String courseIdentifier) {
        return userRepository.createEnrolmentRelationship(username, courseIdentifier);
    }

    public List<Course> getAllEnrolledCoursesByUsername(String username) {
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }

}
