package dev.farhan.springbootneo4j.repositories;

import dev.farhan.springbootneo4j.models.User;
import dev.farhan.springbootneo4j.queryresults.CourseEnrolmentQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    @Query("MATCH (user:USER), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "return EXISTS ((user)-[:ENROLLED_IN]->(course))")
    Boolean findEnrollmentStatus(String username, String identifier);

    @Query("MATCH (user:USER), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "CREATE (user)-[:ENROLLED_IN]->(course) RETURN user, course")
    CourseEnrolmentQueryResult createEnrollmentRelationship(String username, String identifier);
}
