package com.mlconti.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mlconti.demo.domain.Item_pedido;

public interface ItemPedidoRepository extends JpaRepository<Item_pedido, Integer> {
    
}
