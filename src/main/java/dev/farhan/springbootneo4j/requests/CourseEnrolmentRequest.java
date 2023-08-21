package dev.farhan.springbootneo4j.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseEnrolmentRequest {

    private String courseIdentifier; //course identifier
}
