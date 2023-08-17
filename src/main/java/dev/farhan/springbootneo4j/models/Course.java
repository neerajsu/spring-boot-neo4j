package dev.farhan.springbootneo4j.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Node
public class Course {

    @Id @GeneratedValue
    private Long id;
    private String identifier;
    private String title;
    private String teacher;


}
