package com.example.cityhospitalservice.controller;

import com.example.cityhospitalservice.domain.dto.UserDto;
import com.example.cityhospitalservice.service.connection.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital/api/v1/dr")
public class DoctorController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAll(
            Principal principal
    ){
        return ResponseEntity.ok(userService.getDoctors(principal));
    }
}
