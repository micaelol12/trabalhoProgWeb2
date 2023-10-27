package com.mlconti.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Pedido;

@Repository
public interface PedidoRespository extends JpaRepository<Pedido, Integer> {

}
