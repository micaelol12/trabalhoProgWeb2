package com.mlconti.demo.repository;

import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
