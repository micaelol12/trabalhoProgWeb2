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
import com.mlconti.demo.domain.Produto;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.ProdutoRespository;
import com.mlconti.demo.services.ProdutoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoServices produtoServices;

    @Autowired
    private ProdutoRespository produtoRespository;

    @PostMapping("/categoria/{id_categoria}")
    public ResponseEntity<Produto> createProduto(@PathVariable Integer id_categoria,
            @Valid @RequestBody Produto pProduto) {
        Produto novoProduto = produtoServices.create(pProduto, id_categoria);

        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoProduto.getCd_produto()).toUri();

        return ResponseEntity.created(vUri).body(novoProduto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> produtos = produtoRespository.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Integer id) {
        Produto produto = produtoRespository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto " + id + " não encontrado"));

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/{id}/categoria/{categoria_id}")
    public ResponseEntity<Produto> updateCategory(@Valid @PathVariable Integer id,
            @RequestBody Produto pProduto, @Valid @PathVariable Integer categoria_id) {

        Produto produtoAtual = produtoServices.updProduto(id, pProduto, categoria_id);
        return ResponseEntity.ok().body(produtoAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteError> deleteProduto(@PathVariable Integer id) {
        return produtoServices.deleteProduto(id);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> findByName(@PathVariable String nome) {
        return ResponseEntity.ok().body(produtoRespository.findByName(nome.toUpperCase()));
    }

   @GetMapping("/categoria/{id_categoria}")
    public ResponseEntity<List<Produto>> findByCategoria(@PathVariable Integer id_categoria) {
        return ResponseEntity.ok().body(produtoRespository.findByCategoria(id_categoria));
    }

}
