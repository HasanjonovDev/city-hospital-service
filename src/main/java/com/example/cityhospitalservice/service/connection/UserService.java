package com.example.cityhospitalservice.service.connection;

import com.example.cityhospitalservice.domain.dto.UserDto;
import com.example.cityhospitalservice.domain.entity.JwtToken;
import com.example.cityhospitalservice.exception.DataNotFoundException;
import com.example.cityhospitalservice.repoistory.JwtTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${services.user-service-url}")
    private String userServiceUrl;
    private final JwtTokenRepository jwtTokenRepository;
    private final RestTemplate restTemplate;

    public List<UserDto> getDoctors(Principal principal){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(userServiceUrl + "/api/v1/get/doctors");
        JwtToken jwtTokenEntity = jwtTokenRepository.findById(principal.getName()).orElseThrow(() -> new DataNotFoundException("Jwt token not found or expired!"));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("authorization","Bearer " + jwtTokenEntity.getToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity, List.class).getBody();
    }

}
