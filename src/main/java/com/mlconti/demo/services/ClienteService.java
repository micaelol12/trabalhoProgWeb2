package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.Cliente;
import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.domain.Endereco;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.ClienteRepository;
import com.mlconti.demo.repository.EnderecoRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Cliente create(Cliente pCliente, Integer pIdEndereco) {
        Endereco endereco = enderecoRepository.findById(pIdEndereco)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço " + pIdEndereco + " não encontrado!"));

        pCliente.setCd_cliente(null);
        pCliente.setEndereco(endereco);

        return clienteRepository.save(pCliente);
    }

    public ResponseEntity<DeleteError> deleteCliente(Integer pIdCliente) {
        DeleteError mensagem = new DeleteError();

        Optional<Cliente> cliente = clienteRepository.findById(pIdCliente);

        if (cliente.isEmpty()) {
            mensagem.setMensagem("Cliente com o id " + pIdCliente + " não encontrado");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        try {
            clienteRepository.deleteById(pIdCliente);

        } catch (DataIntegrityViolationException e) {
            mensagem.setMensagem(
                    "Cliente " + pIdCliente + " não pode ser excluído.Pois possui pedidos alocados");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        mensagem.setMensagem("OK");
        mensagem.setType("");
        return ResponseEntity.ok().body(mensagem);
    }

    public Cliente updateCliente(Integer cId, Cliente pCliente, Integer eId) {
        Cliente clienteAtual = clienteRepository.findById(cId).orElseThrow(
                () -> new ObjectNotFoundException("Cliente com o id " + cId + " não encontrado"));

        Endereco novoEndereco = enderecoRepository.findById(eId).orElseThrow(
                () -> new ObjectNotFoundException("Endereco com o id  " + eId + " não encontrado"));

        clienteAtual.setDs_email(pCliente.getDs_email());
        clienteAtual.setNm_cliente(pCliente.getNm_cliente());
        clienteAtual.setVl_limite_cred(pCliente.getVl_limite_cred());
        clienteAtual.setEndereco(novoEndereco);
        clienteAtual.setNr_telefone(pCliente.getNr_telefone());

        return clienteRepository.save(clienteAtual);
    }
}
