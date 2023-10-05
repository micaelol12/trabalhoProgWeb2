package com.mlconti.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    public ResponseEntity<List<Aula>> findAll() {
        List<Aula> listaAulas = aulaRepository.findAll();
        return ResponseEntity.ok().body(listaAulas);
    }

    @PostMapping(value = "/{id_aula}/aluno/{id_aluno}")
    public ResponseEntity<Aula> insAlunoAula(@Valid @PathVariable Integer id_aula,
            @Valid @PathVariable Integer id_aluno) {
        Aula aula = aulaRepository.findById(id_aula)
                .orElseThrow(() -> new ObjectNotFoundException("Aula " + id_aula + " não encontrada"));
        Aluno aluno = alunoRepository.findById(id_aluno)
                .orElseThrow(() -> new ObjectNotFoundException("Aluno " + id_aluno + " não encontrada"));

        aula.addAluno(aluno);

        aulaRepository.save(aula);

        return ResponseEntity.ok().body(aula);
    }

    
    @DeleteMapping(value = "/{id_aula}/aluno/{id_aluno}")
    public ResponseEntity<Aula> deleteAlunoAula(@Valid @PathVariable Integer id_aula,
            @Valid @PathVariable Integer id_aluno) {
        Aula aula = aulaRepository.findById(id_aula)
                .orElseThrow(() -> new ObjectNotFoundException("Aula " + id_aula + " não encontrada"));
        Aluno aluno = alunoRepository.findById(id_aluno)
                .orElseThrow(() -> new ObjectNotFoundException("Aluno " + id_aluno + " não encontrada"));

        aula.removeAluno(aluno);

        aulaRepository.save(aula);

        return ResponseEntity.ok().body(aula);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aula>> findByID(@PathVariable Integer id) {
        Optional<Aula> aula = aulaRepository.findById(id);

        return ResponseEntity.ok().body(aula);
    }

}
