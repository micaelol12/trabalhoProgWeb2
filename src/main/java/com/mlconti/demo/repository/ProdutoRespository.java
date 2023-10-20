package com.mlconti.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Produto;

@Repository
public interface ProdutoRespository extends JpaRepository<Produto, Integer> {

}
