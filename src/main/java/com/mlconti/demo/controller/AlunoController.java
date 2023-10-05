package com.mlconti.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mlconti.demo.domain.Aluno;
import com.mlconti.demo.domain.Aula;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.AlunoRepository;
import com.mlconti.demo.repository.AulaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

        @Autowired
    private AulaRepository aulaRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> alunos = alunoRepository.findAll();
        return ResponseEntity.ok().body(alunos);
    }



    @PostMapping(value = "/{id_aluno}/aula/{id_aula}")
    public ResponseEntity<Aluno> insAulaAluno(@Valid @PathVariable Integer id_aluno, @Valid @PathVariable Integer id_aula){
            Aluno aluno = alunoRepository.findById(id_aluno).orElseThrow(()-> new ObjectNotFoundException("Aluno " + id_aluno + "não encontrado"));
            Aula aula = aulaRepository.findById(id_aula).orElseThrow(()-> new ObjectNotFoundException("Aula " + id_aula + "não encontrada"));

            aluno.addAula(aula);

            alunoRepository.save(aluno);

            return ResponseEntity.ok().body(aluno);
    }
}
