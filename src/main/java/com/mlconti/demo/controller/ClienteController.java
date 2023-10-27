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

import com.mlconti.demo.domain.Cliente;
import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.ClienteRepository;
import com.mlconti.demo.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @PostMapping("/endereco/{id_endereco}")
    public ResponseEntity<Cliente> createProduto(@PathVariable Integer id_endereco,
            @Valid @RequestBody Cliente pCliente) {
        Cliente novoCliente = clienteService.create(pCliente, id_endereco);

        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoCliente.getCd_cliente()).toUri();

        return ResponseEntity.created(vUri).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> produtos = clienteRepository.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
        Cliente produto = clienteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente " + id + " não encontrado"));

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/{id}/endereco/{cliente_id}")
    public ResponseEntity<Cliente> updateCliente(@Valid @PathVariable Integer id,

            @RequestBody Cliente pCliente, @Valid @PathVariable Integer cliente_id) {

        Cliente produtoAtual = clienteService.updateCliente(id, pCliente, cliente_id);
        return ResponseEntity.ok().body(produtoAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteError> deleteEndereco(@PathVariable Integer id) {

        return clienteService.deleteCliente(id);

    }
}
