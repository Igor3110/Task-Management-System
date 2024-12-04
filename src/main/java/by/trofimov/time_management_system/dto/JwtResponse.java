package by.trofimov.time_management_system.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * Dto of the response when receiving a token.
 */
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private String token;

}
