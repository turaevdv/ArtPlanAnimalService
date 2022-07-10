package ru.turaev.animalservice.securityutil;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;
import ru.turaev.animalservice.exception.AuthenticationException;
import ru.turaev.animalservice.service.AuthorizationService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {
    private final AuthorizationService authorizationService;

    @Value("${jwt.header}")
    private String HEADER;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) servletRequest).getHeader(HEADER);
        if (token == null || token.isBlank()) {
            throw new AuthenticationException("Token not found");
        }
        if (!authorizationService.isTokenValid(token)) {
            throw new AuthenticationException("Token is invalid");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
