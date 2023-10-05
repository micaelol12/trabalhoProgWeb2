package com.mlconti.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.mlconti.demo.domain.Departamento;
import com.mlconti.demo.domain.Funcionario;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.DepartamentoRepository;
import com.mlconti.demo.repository.FuncionarioRepository;
import com.mlconti.demo.services.FuncServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcRepository;

    @Autowired
    private DepartamentoRepository deptoRepository;

    @Autowired
    private FuncServices funcServices;

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> funcionarios = funcRepository.findAll();

        return ResponseEntity.ok().body(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Funcionario>> findByID(@PathVariable Integer id) {
        Optional<Funcionario> funcionario = funcRepository.findById(id);

        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Funcionario>> findByName(@PathVariable String nome) {
        List<Funcionario> funcionarios = funcRepository.findByName(nome);

        return ResponseEntity.ok().body(funcionarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        funcServices.delFunct(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id_depto}")
    public ResponseEntity<Funcionario> insFunc(@PathVariable Integer id_depto,
            @Valid @RequestBody Funcionario pFuncionario) {
        Funcionario novoFuncionario = funcServices.insFunc(pFuncionario, id_depto);

        URI vUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoFuncionario.getId_funcionario()).toUri();

        return ResponseEntity.created(vUri).body(novoFuncionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updFunc(@Valid @PathVariable Integer id, @RequestBody Funcionario pFuncionario) {
        Funcionario funcUpdate = funcServices.updFunc(id, pFuncionario);
        URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("funcionario/{id}")
                .buildAndExpand(funcUpdate.getId_funcionario()).toUri();
        return ResponseEntity.created(vUri).body(funcUpdate);
    }

    @PutMapping(value = "{idFunc}/depto/{idDepto}")
    public ResponseEntity<Funcionario> updFuncDepto(@Valid @PathVariable Integer idFunc,
            @Valid @PathVariable Integer idDpeto) {
        Funcionario funcUpdated = funcRepository.findById(idFunc)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário" + idFunc + "Não encontrado"));
        Departamento newDepto = deptoRepository.findById(idDpeto)
                .orElseThrow(() -> new ObjectNotFoundException("Departameto" + idDpeto + "Não encontrado"));

        funcUpdated.setDepartamento_pai(newDepto);
        funcRepository.save(funcUpdated);

        URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("funcionario/{id}")
                .buildAndExpand(funcUpdated.getId_funcionario()).toUri();
        return ResponseEntity.created(vUri).body(funcUpdated);
    }

}
