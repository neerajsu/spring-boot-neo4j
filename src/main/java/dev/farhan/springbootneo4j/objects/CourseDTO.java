package dev.farhan.springbootneo4j.objects;

import dev.farhan.springbootneo4j.models.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDTO {
    private String identifier;
    private String title;
    private String teacher;
    private List<Lesson> lessons;
    private boolean isEnrolled;
}
