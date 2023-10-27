package com.mlconti.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.domain.Pedido;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.PedidoRespository;
import com.mlconti.demo.services.PedidoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    private PedidoRespository pedidoRespository;

    @Autowired
    PedidoServices pedidoServices;

    @PostMapping("/cliente/{id_cliente}")
    public ResponseEntity<Pedido> createProduto(@PathVariable Integer id_cliente,
            @Valid @RequestBody Pedido pPedido) {
        Pedido novoCliente = pedidoServices.create(pPedido, id_cliente);

        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoCliente.getNr_pedido()).toUri();

        return ResponseEntity.created(vUri).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> produtos = pedidoRespository.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Integer id) {
        Pedido produto = pedidoRespository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido " + id + " não encontrado"));

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/{id}/cliente/{cliente_id}")
    public ResponseEntity<Pedido> updatePedido(@Valid @PathVariable Integer id,

            @RequestBody Pedido pPedido, @Valid @PathVariable Integer cliente_id) {

        Pedido pedidoAtual = pedidoServices.updatePedido(id, pPedido, cliente_id);
        return ResponseEntity.ok().body(pedidoAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteError> deleteEndereco(@PathVariable Integer id) {

        return pedidoServices.deletePedido(id);

    }
}
