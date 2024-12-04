package by.trofimov.time_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto to request to receive a token.
 */
@Getter
@Setter
@AllArgsConstructor
public class JwtRequest {

    private String email;
    private String password;

}
