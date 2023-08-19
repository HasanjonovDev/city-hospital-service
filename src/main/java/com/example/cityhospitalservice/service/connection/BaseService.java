package com.example.cityhospitalservice.service.connection;

import com.example.cityhospitalservice.domain.entity.JwtToken;
import com.example.cityhospitalservice.exception.DataNotFoundException;
import com.example.cityhospitalservice.repoistory.JwtTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseService {
    private final JwtTokenRepository jwtTokenRepository;

    public HttpHeaders configureHttpHeaders(String username) {
        HttpHeaders headers = new HttpHeaders();
        JwtToken jwtToken = jwtTokenRepository.findById(username).orElseThrow(() -> new DataNotFoundException("Jwt not found!"));
        headers.add("Authorization","Bearer " + jwtToken.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
