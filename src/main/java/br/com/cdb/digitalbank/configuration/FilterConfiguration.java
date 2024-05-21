package br.com.cdb.digitalbank.configuration;

import br.com.cdb.digitalbank.controller.exceptions.StandardError;
import br.com.cdb.digitalbank.service.authentication.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;

@Component
public class FilterConfiguration extends OncePerRequestFilter {

    @Autowired
    private JwtService auth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = request.getHeader("Authorization");
        try {
            if (token != null) {
                var decodedJWT = auth.decode(token.replace("Bearer ", ""));
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), decodedJWT.getClaim("password"), Collections.emptyList())
                );
            }
        } catch (Exception e) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            var error = new StandardError(Instant.now(), 401, "Token inválido", "Token inválido", request.getRequestURI());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), error);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
