package com.example.cityhospitalservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private UUID id;
    @NotBlank(message = "Enter email please")
    private String email;
    @NotBlank(message = "Enter password please")
    private String password;
    private List<RoleDto> roles;
}
