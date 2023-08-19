package com.example.cityhospitalservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Entity(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtToken {
    @Id
    private String username;
    private String token;
}
