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
import com.mlconti.demo.domain.Item_pedido;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.ItemPedidoRepository;
import com.mlconti.demo.services.ItemPedidoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/item_pedido")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ItemPedidoServices itemPedidoServices;

    @PostMapping("/item/{id_item}/pedido/{id_pedido}")
    public ResponseEntity<Item_pedido> createItemPedido(@PathVariable Integer id_item, @PathVariable Integer id_pedido,
            @Valid @RequestBody Item_pedido pItem_pedido) {

        Item_pedido novoItemPedido = itemPedidoServices.create(pItem_pedido, id_item, id_pedido);

        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoItemPedido.getPedido()).toUri();

        return ResponseEntity.created(vUri).body(novoItemPedido);
    }

    @GetMapping
    public ResponseEntity<List<Item_pedido>> getAll() {
        List<Item_pedido> produtos = itemPedidoRepository.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item_pedido> getById(@PathVariable Integer id) {
        Item_pedido produto = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Item_pedido" + id + " não encontrado"));

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/{id}/pedido/{pedido_id}/produto/{produto_id}")
    public ResponseEntity<Item_pedido> updateItemPedido(@Valid @PathVariable Integer id,

            @RequestBody Item_pedido pItem_pedido, @Valid @PathVariable Integer pedido_id,
            @Valid @PathVariable Integer produto_id) {

        Item_pedido produtoAtual = itemPedidoServices.updateItemPedido(id, pItem_pedido, pedido_id, produto_id);
        return ResponseEntity.ok().body(produtoAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteError> deleteEndereco(@PathVariable Integer id) {

        return itemPedidoServices.deleteItemPedido(id);

    }
}
