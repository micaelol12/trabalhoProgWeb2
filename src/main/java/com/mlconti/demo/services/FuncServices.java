package com.mlconti.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.Departamento;
import com.mlconti.demo.domain.Funcionario;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.DepartamentoRepository;
import com.mlconti.demo.repository.FuncionarioRepository;

@Service
public class FuncServices {
    @Autowired
    private FuncionarioRepository funcRepository;

    @Autowired
    private DepartamentoRepository deptoRrepository;

    public Funcionario insFunc(Funcionario pFuncionario, Integer pIdDepto) {
        Departamento deptoFunc = deptoRrepository.findById(pIdDepto)
                .orElseThrow(() -> new ObjectNotFoundException("Departamento " + pIdDepto + " não encontrado!"));

        pFuncionario.setId_funcionario(null);
        pFuncionario.setDepartamento_pai(deptoFunc);

        return funcRepository.save(pFuncionario);
    }

    public void delFunct(Integer pIdFuncionario) {
        funcRepository.findById(pIdFuncionario)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionario " + pIdFuncionario + " Não encontrado"));

        funcRepository.deleteById(pIdFuncionario);
    }

    public Funcionario updFunc(Integer pId, Funcionario pFuncionario) {
        Funcionario funcAtual = funcRepository.findById(pId)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário" + pId + " não encontrado"));

        funcAtual.setNm_funcionario((pFuncionario.getNm_funcionario()));
        return funcRepository.save(funcAtual);
    }

  
}
