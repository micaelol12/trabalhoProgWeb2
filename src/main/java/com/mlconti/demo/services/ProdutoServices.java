package com.mlconti.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.Categoria;
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

    public Produto create(Produto pProduto, Integer pIdCategoria) {
        Categoria categoriaProduto = caterogiaRepository.findById(pIdCategoria)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria " + pIdCategoria + " não encontrada!"));

        pProduto.setCd_produto(null);
        pProduto.setCategoria(categoriaProduto);

        return produtoRespository.save(pProduto);
    }
}
