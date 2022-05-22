package ma.authentification.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ma.authentification.project.entities.User;

@Data
@AllArgsConstructor
public class JwtResponse {
    private User user;
    private String jwtToken;
}
