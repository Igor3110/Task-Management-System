package by.trofimov.time_management_system.token;

import java.util.*;
import java.security.Key;
import java.time.Duration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static by.trofimov.time_management_system.util.Constant.ROLES;

/**
 A class for working with a token.
 */
@Component
public class JwtTokenManager {

    private static final String SECRET =
            "4vMGuR+w8104vMGuR+w3w8104vMGuRw8104vMGuRw3w8104vMGuRw8104vMGuRw3w8104vMGuRw8104vMGuRw3w8104vMGuRw8104vMGuRw3w8104vMGuRw8104vMGuRw3w8104vMGuRw8104vMGuR";
    private final Key key;
    @Value("${jwt.token.duration}")
    private Duration jwtLifeTime;

    public JwtTokenManager() {
        this.key = new SecretKeySpec(SECRET.getBytes(), HS256.getJcaName());
    }

    /**
     * @param userDetails - Class for authentication in Spring Security.
     * @return - The method returns a JWT token.
     */
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put(ROLES, roles);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifeTime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(key, HS256)
                .compact();
    }

    /**
     * @return - The method returns Username from the JWT token.
     */
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * @return - The method returns the user's Roles.
     */
    @SuppressWarnings("unchecked")
    public List<String> getUserRoles(String token) {
        return getAllClaimsFromToken(token).get(ROLES, List.class);
    }

    /**
     * @return - The method returns Claims from the token.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
