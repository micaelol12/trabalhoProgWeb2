package com.mlconti.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Produto;

@Repository
public interface ProdutoRespository extends JpaRepository<Produto, Integer> {

    @Query("SELECT objeto_func FROM Produto objeto_func WHERE objeto_func.categoria.cd_categoria = :cd_categoria ORDER BY nm_produto" )
     List<Produto> findByCategoria(@Param(value="cd_categoria") Integer cd_categoria);

    @Query("SELECT objeto_func FROM Produto objeto_func WHERE UPPER(objeto_func.nm_produto) LIKE %:pNome% ORDER BY nm_produto")
    List<Produto> findByName(@Param(value = "pNome") String pNome);
}
