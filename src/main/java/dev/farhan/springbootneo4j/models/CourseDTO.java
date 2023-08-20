package dev.farhan.springbootneo4j.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDTO {
    private String identifier;
    private String title;
    private String teacher;
}
