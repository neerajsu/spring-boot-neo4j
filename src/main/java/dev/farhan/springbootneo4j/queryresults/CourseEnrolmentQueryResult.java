package dev.farhan.springbootneo4j.queryresults;


import dev.farhan.springbootneo4j.models.Course;
import dev.farhan.springbootneo4j.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CourseEnrolmentQueryResult {

    private User user;
    private Course course;
}
