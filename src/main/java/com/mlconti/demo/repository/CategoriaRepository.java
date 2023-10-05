package com.mlconti.demo.repository;

import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
