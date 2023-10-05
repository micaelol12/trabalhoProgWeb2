package com.mlconti.demo.repository;

import com.mlconti.demo.domain.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT objeto_func FROM Funcionario objeto_func WHERE objeto_func.departamento_pai.id_depto = :pIdDepto ORDER BY nm_funcionario")
    List<Funcionario> findByDepto(@Param(value = "pIdDepto") Integer pIdDepto);

    @Query("SELECT objeto_func FROM Funcionario objeto_func WHERE objeto_func.nm_funcionario ILIKE %:pNome% ORDER BY nm_funcionario")
    List<Funcionario> findByName(@Param(value = "pNome") String pNome);
}
