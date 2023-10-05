package com.mlconti.demo.repository;

import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Aula;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
