package com.mlconti.demo.repository;

import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Departamento;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Integer> {
    
}
