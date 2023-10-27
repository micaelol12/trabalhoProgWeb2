package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.Cliente;
import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.domain.Pedido;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.ClienteRepository;
import com.mlconti.demo.repository.PedidoRespository;

@Service
public class PedidoServices {
    @Autowired
    PedidoRespository pedidoRespository;
    @Autowired
    ClienteRepository clienteRepository;

    public Pedido create(Pedido pPedido, Integer pIdCliente) {
        Cliente cliente = clienteRepository.findById(pIdCliente)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente " + pIdCliente + " não encontrado!"));

        pPedido.setNr_pedido(null);
        pPedido.setCliente(cliente);

        return pedidoRespository.save(pPedido);
    }

    public Pedido updatePedido(Integer pId, Pedido pPedido, Integer cId) {
        Pedido pedidoAtual = pedidoRespository.findById(pId).orElseThrow(
                () -> new ObjectNotFoundException("Pedido com o id " + pId + " não encontrado"));

        Cliente novoCliente = clienteRepository.findById(cId).orElseThrow(
                () -> new ObjectNotFoundException("Cliente com o id  " + cId + " não encontrado"));

        pedidoAtual.setDt_emissao(pPedido.getDt_emissao());
        pedidoAtual.setVl_total(pPedido.getVl_total());
        pedidoAtual.setCliente(novoCliente);

        return pedidoRespository.save(pedidoAtual);
    }

    public ResponseEntity<DeleteError> deletePedido(Integer pIdPedido) {
        DeleteError mensagem = new DeleteError();

        Optional<Pedido> pedido = pedidoRespository.findById(pIdPedido);

        if (pedido.isEmpty()) {
            mensagem.setMensagem("Pedido com o id " + pIdPedido + " não encontrado");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        try {
            pedidoRespository.deleteById(pIdPedido);

        } catch (DataIntegrityViolationException e) {
            mensagem.setMensagem(
                    "Cliente " + pIdPedido + " não pode ser excluído.Pois possui itens alocados");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        mensagem.setMensagem("OK");
        mensagem.setType("");
        return ResponseEntity.ok().body(mensagem);
    }
}
