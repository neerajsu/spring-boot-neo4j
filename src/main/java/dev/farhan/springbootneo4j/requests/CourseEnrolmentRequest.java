package dev.farhan.springbootneo4j.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseEnrolmentRequest {

    private String identifier; //course identifier
}
