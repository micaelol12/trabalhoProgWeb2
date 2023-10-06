package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.Categoria;
import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.exceptions.DataIntegryViolationException;
import com.mlconti.demo.repository.CategoriaRepository;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public DeleteError deleteCategoria(Integer pIdCategoria) {
        Optional<Categoria> categoria = categoriaRepository.findById(pIdCategoria);
        DeleteError mensagem = new DeleteError();

        if (categoria.isEmpty()) {
            mensagem.setMensagem("Categoria com o id " + pIdCategoria + " não encontrada");
            mensagem.setType("ERRO");
            return mensagem;
        }

        try {
            categoriaRepository.deleteById(pIdCategoria);

        } catch (DataIntegrityViolationException e) {
            mensagem.setMensagem("Categoria " + pIdCategoria + " não pode ser excluída. Pois possui categorias alocadass!");
            mensagem.setType("ERRO");
            return mensagem;
        }
        
        mensagem.setMensagem("OK");
        mensagem.setType("");
        return mensagem;
    }
}
