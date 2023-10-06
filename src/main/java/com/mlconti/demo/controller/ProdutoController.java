package com.mlconti.demo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mlconti.demo.domain.Produto;
import com.mlconti.demo.services.ProdutoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoServices produtoServices;

    @PostMapping("/categoria/{id_categoria}")
    public ResponseEntity<Produto> createProduto(@PathVariable Integer id_categoria,
            @Valid @RequestBody Produto pProduto) {
        Produto novoProduto = produtoServices.create(pProduto,id_categoria);

        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoProduto.getCd_produto()).toUri();

        return ResponseEntity.created(vUri).body(novoProduto);
    }
}
