package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.Categoria;
import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.domain.Produto;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.CategoriaRepository;
import com.mlconti.demo.repository.ProdutoRespository;

@Service
public class ProdutoServices {

    @Autowired
    private CategoriaRepository caterogiaRepository;

    @Autowired
    private ProdutoRespository produtoRespository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto create(Produto pProduto, Integer pIdCategoria) {
        Categoria categoriaProduto = caterogiaRepository.findById(pIdCategoria)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria " + pIdCategoria + " não encontrada!"));

        pProduto.setCd_produto(null);
        pProduto.setCategoria(categoriaProduto);

        return produtoRespository.save(pProduto);
    }

    public Produto updProduto(Integer pId, Produto pProduto, Integer cId) {
        Produto produtoAtual = produtoRespository.findById(pId).orElseThrow(
                () -> new ObjectNotFoundException("Produto  " + pId + " não encontrado"));

        Categoria novaCategoria = categoriaRepository.findById(cId).orElseThrow(
                () -> new ObjectNotFoundException("Categoria  " + cId + " não encontrada"));

        produtoAtual.setNm_produto(pProduto.getNm_produto());
        produtoAtual.setQt_estoque(pProduto.getQt_estoque());
        produtoAtual.setVl_produto(pProduto.getVl_produto());
        produtoAtual.setCategoria(novaCategoria);

        return produtoRespository.save(produtoAtual);
    }

    public ResponseEntity<DeleteError> deleteProduto(Integer pIdProduto) {
        DeleteError mensagem = new DeleteError();
        Optional<Produto> produto = produtoRespository.findById(pIdProduto);

        if (produto.isEmpty()) {
            mensagem.setMensagem("Produto com o id " + pIdProduto + " não encontradi");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        try {
            produtoRespository.deleteById(pIdProduto);

        } catch (DataIntegrityViolationException e) {
            mensagem.setMensagem(
                    "Produto " + pIdProduto + " não pode ser excluído.");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        mensagem.setMensagem("OK");
        mensagem.setType("");
        return ResponseEntity.ok().body(mensagem);
    }

}
