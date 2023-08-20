package dev.farhan.springbootneo4j.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequest {

    private String name;
    private String username;
    private String password;
    private String roles;

}
