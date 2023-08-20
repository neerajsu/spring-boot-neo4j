package dev.farhan.springbootneo4j.objects;

import dev.farhan.springbootneo4j.models.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseEnrolmentDTO {

    private String username;
    private Course course;
    private String name;
}
