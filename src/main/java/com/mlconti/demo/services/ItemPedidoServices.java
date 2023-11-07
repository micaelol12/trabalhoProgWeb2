package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.domain.Item_pedido;
import com.mlconti.demo.domain.Pedido;
import com.mlconti.demo.domain.Produto;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.ItemPedidoRepository;
import com.mlconti.demo.repository.PedidoRespository;
import com.mlconti.demo.repository.ProdutoRespository;

@Service
public class ItemPedidoServices {

    @Autowired
    ProdutoRespository produtoRespository;
    @Autowired
    PedidoRespository pedidoRespository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public Item_pedido create(Item_pedido pItem_pedido, Integer idProduto, Integer idPedido) {
        Produto produto = produtoRespository.findById(idProduto)
                .orElseThrow(() -> new ObjectNotFoundException("Produto " + idProduto + " não encontrado!"));

        Pedido pedido = pedidoRespository.findById(idPedido)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido " + idPedido + " não encontrado!"));

        pItem_pedido.setCd_itemProduto(null);
        pItem_pedido.setProduto(produto);
        pItem_pedido.setPedido(pedido);

        return itemPedidoRepository.save(pItem_pedido);
    }

    public Item_pedido updateItemPedido(Integer ipId, Item_pedido ipItem_pedido, Integer pId, Integer iId) {
        Item_pedido item_pedidoAtual = itemPedidoRepository.findById(ipId).orElseThrow(
                () -> new ObjectNotFoundException("Item Pedido com o id " + ipId + " não encontrado"));

        Produto novoProduto = produtoRespository.findById(pId).orElseThrow(
                () -> new ObjectNotFoundException("Produto com o id  " + pId + " não encontrado"));

        Pedido novoPedido = pedidoRespository.findById(pId).orElseThrow(
                () -> new ObjectNotFoundException("Pedido com o id  " + iId + " não encontrado"));

        item_pedidoAtual.setPedido(novoPedido);
        item_pedidoAtual.setProduto(novoProduto);
        item_pedidoAtual.setQt_produto(ipItem_pedido.getQt_produto());
        item_pedidoAtual.setVl_produto(ipItem_pedido.getVl_produto());

        return itemPedidoRepository.save(item_pedidoAtual);
    }

    public ResponseEntity<DeleteError> deleteItemPedido(Integer pIdItemPedido) {
        DeleteError mensagem = new DeleteError();

        Optional<Item_pedido> item_pedido = itemPedidoRepository.findById(pIdItemPedido);

        if (item_pedido.isEmpty()) {
            mensagem.setMensagem("Item_pedido com o id " + pIdItemPedido + " não encontrado");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        } 

        itemPedidoRepository.deleteById(pIdItemPedido);

        mensagem.setMensagem("OK");
        mensagem.setType("");
        return ResponseEntity.ok().body(mensagem);
    }

}
