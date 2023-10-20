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
import com.mlconti.demo.domain.Municipio;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.MunicipioRepository;
import com.mlconti.demo.services.MunicipioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/municipio")
public class MunicipController {
    
    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioServices municipioServices;

   

    @PostMapping
    public ResponseEntity<Municipio> create(@Valid @RequestBody Municipio pMunicipio) {
        pMunicipio.setCd_municipio(null);
        municipioRepository.save(pMunicipio);
        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/municipio/{id}")
                .buildAndExpand(pMunicipio.getCd_municipio()).toUri();
        return ResponseEntity.created(vUri).body(pMunicipio);
    }

    @GetMapping
    public ResponseEntity<List<Municipio>> getAll() {
        List<Municipio> categorias = municipioRepository.findAll();
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Municipio> getById(@PathVariable Integer id) {
        Municipio departamento = municipioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Municipio " + id + " não encontrado"));

        return ResponseEntity.ok().body(departamento);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Municipio> updateCategory(@Valid @PathVariable Integer id,
            @RequestBody Municipio pCategoria) {
        Municipio categoria = municipioServices.updateMunicipio(pCategoria, id);
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteError> deleteCategoria(@PathVariable Integer id) {

        return municipioServices.deleteMunicipio(id);
    }
}
