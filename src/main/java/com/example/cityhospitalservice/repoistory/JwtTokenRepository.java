package com.example.cityhospitalservice.repoistory;

import com.example.cityhospitalservice.domain.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken,String> {
}
