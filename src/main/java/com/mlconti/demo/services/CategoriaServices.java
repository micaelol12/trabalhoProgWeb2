package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.Categoria;
import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.CategoriaRepository;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria updateCategoria(Categoria pCategoria,Integer pId) {
         Categoria categoriaAtual = categoriaRepository.findById(pId).orElseThrow(
                () -> new ObjectNotFoundException("Categoria  " + pId + " não encontrada"));

        categoriaAtual.setDs_categoria(pCategoria.getDs_categoria());
        return categoriaRepository.save(categoriaAtual);
    }

    public ResponseEntity<DeleteError> deleteCategoria(Integer pIdCategoria) {
        Optional<Categoria> categoria = categoriaRepository.findById(pIdCategoria);
        DeleteError mensagem = new DeleteError();

        if (categoria.isEmpty()) {
            mensagem.setMensagem("Categoria com o id " + pIdCategoria + " não encontrada");
            mensagem.setType("ERRO");

            return ResponseEntity.badRequest().body(mensagem);
        }

        try {
            categoriaRepository.deleteById(pIdCategoria);

        } catch (DataIntegrityViolationException e) {
            mensagem.setMensagem(
                    "Categoria " + pIdCategoria + " não pode ser excluída. Pois possui produtos alocados!");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        mensagem.setMensagem("OK");
        mensagem.setType("");
        return ResponseEntity.ok().body(mensagem);
    }
}
