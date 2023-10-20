package com.mlconti.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    
}
