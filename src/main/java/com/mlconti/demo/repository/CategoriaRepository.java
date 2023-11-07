package com.mlconti.demo.repository;

import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
  @Query("SELECT objeto_func FROM Categoria objeto_func WHERE UPPER(objeto_func.ds_categoria) LIKE %:pNome% ORDER BY ds_categoria")
    List<Categoria> findByName(@Param(value = "pNome") String pNome);
}
