package by.trofimov.time_management_system.config;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import by.trofimov.time_management_system.token.JwtTokenManager;
import static by.trofimov.time_management_system.util.Constant.BEARER_;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Filter for whether the client has a token.
 */
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    /**
     * Method for whether the client has a token.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION);
        if (authorization != null && authorization.startsWith(BEARER_)) {
            String jwt = authorization.substring(7);
            try {
                    String username = jwtTokenManager.getUsername(jwt);
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    jwtTokenManager.getUserRoles(jwt).stream()
                                            .map(SimpleGrantedAuthority::new)
                                            .toList()
                            );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (MalformedJwtException | ExpiredJwtException exception) {
                log.debug(exception.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

}
