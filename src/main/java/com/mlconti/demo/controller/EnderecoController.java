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
import com.mlconti.demo.domain.Endereco;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.EnderecoRepository;
import com.mlconti.demo.services.EnderecoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoServices enderecoServices;

    @PostMapping("/municipio/{id_municipio}")
    public ResponseEntity<Endereco> createProduto(@PathVariable Integer id_municipio,
            @Valid @RequestBody Endereco pEndereco) {
        Endereco novoEndereco = enderecoServices.create(pEndereco, id_municipio);

        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoEndereco.getCd_endereco()).toUri();

        return ResponseEntity.created(vUri).body(novoEndereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
        List<Endereco> produtos = enderecoRepository.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable Integer id) {
        Endereco produto = enderecoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereco " + id + " não encontrado"));

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/{id}/municipio/{municipio_id}")
    public ResponseEntity<Endereco> updateEndereco(@Valid @PathVariable Integer id,

        @RequestBody Endereco pEndereco, @Valid @PathVariable Integer municipio_id) {

        Endereco produtoAtual = enderecoServices.updateEndereco(id, pEndereco, municipio_id);
        return ResponseEntity.ok().body(produtoAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteError> deleteEndereco(@PathVariable Integer id) {

        return enderecoServices.deleteEndereco(id);

    }
}
