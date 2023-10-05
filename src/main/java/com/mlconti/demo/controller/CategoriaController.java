package com.mlconti.demo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mlconti.demo.domain.Categoria;
import com.mlconti.demo.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository caterogiaRepository;

    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria pCategoria) {
        pCategoria.setCd_categoria(null);
        caterogiaRepository.save(pCategoria);
        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/categoria/{id}")
                .buildAndExpand(pCategoria.getCd_categoria()).toUri();
        return ResponseEntity.created(vUri).body(pCategoria);
    }
}
