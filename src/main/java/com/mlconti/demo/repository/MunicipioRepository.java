package com.mlconti.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    /*
     * @Query("SELECT objeto_func FROM Funcionario objeto_func WHERE objeto_func.departamento_pai.id_depto = :pIdDepto ORDER BY nm_funcionario"
     * )
     * List<Funcionario> findByDepto(@Param(value="pIdDepto") Integer pIdDepto);
     * 
     * @Query("SELECT objeto_func FROM Funcionario objeto_func WHERE objeto_func.nm_funcionario LIKE %:pNome% ORDER BY nm_funcionario"
     * )
     * List<Funcionario> findByName(@Param(value="pNome") String pNome);
     */

    @Query("SELECT objeto_func FROM Municipio objeto_func WHERE UPPER(objeto_func.nm_localidade) LIKE %:pNome% ORDER BY nm_localidade")
    List<Municipio> findByName(@Param(value = "pNome") String pNome);

    @Query("SELECT objeto_func FROM Municipio objeto_func WHERE UPPER(objeto_func.sg_uf) LIKE %:pNome% ORDER BY sg_uf")
    List<Municipio> findByUF(@Param(value = "pNome") String pNome);
}
