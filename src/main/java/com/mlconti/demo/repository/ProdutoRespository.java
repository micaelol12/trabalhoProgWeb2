package com.mlconti.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mlconti.demo.domain.Produto;

public interface ProdutoRespository extends JpaRepository<Produto, Integer> {

}
