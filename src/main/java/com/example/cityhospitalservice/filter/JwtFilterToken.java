package com.example.cityhospitalservice.filter;

import com.example.cityhospitalservice.domain.entity.JwtToken;
import com.example.cityhospitalservice.exception.NotAcceptable;
import com.example.cityhospitalservice.repoistory.JwtTokenRepository;
import com.example.cityhospitalservice.service.AuthenticationService;
import com.example.cityhospitalservice.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class JwtFilterToken extends OncePerRequestFilter {
    private final JwtTokenRepository jwtTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader("authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        token = token.substring(7);
        Jws<Claims> claimsJws = jwtService.extractToken(token);
        authenticationService.Authenticate(claimsJws.getBody(),request);
        jwtTokenRepository.save(new JwtToken(claimsJws.getBody().getSubject(),token));
        Date expiration = claimsJws.getBody().getExpiration();
        if(new Date().after(expiration)) throw new NotAcceptable("Expired access token!");

        filterChain.doFilter(request,response);
    }
}
