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

import com.mlconti.demo.domain.Categoria;
import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.CategoriaRepository;
import com.mlconti.demo.services.CategoriaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository caterogiaRepository;

    @Autowired
    private CategoriaServices categoriaServices;

    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria pCategoria) {
        pCategoria.setCd_categoria(null);
        caterogiaRepository.save(pCategoria);
        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/categoria/{id}")
                .buildAndExpand(pCategoria.getCd_categoria()).toUri();
        return ResponseEntity.created(vUri).body(pCategoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        List<Categoria> categorias = caterogiaRepository.findAll();
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Integer id) {
        Categoria departamento = caterogiaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria " + id + " não encontrada"));

        return ResponseEntity.ok().body(departamento);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> updateCategory(@Valid @PathVariable Integer id,
            @RequestBody Categoria pCategoria) {
        Categoria categoria = categoriaServices.updateCategoria(pCategoria, id);
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteError> deleteCategoria(@PathVariable Integer id) {

        return categoriaServices.deleteCategoria(id);
    }

    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> findByName(@PathVariable String nome) {
        return ResponseEntity.ok().body(caterogiaRepository.findByName(nome.toUpperCase()));
    }
}
