package com.mlconti.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mlconti.demo.exceptions.DataIntegryViolationException;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.DepartamentoRepository;

@Service
public class DeptServices {

    @Autowired
    private DepartamentoRepository dRepository;

    public void delDepartamento(Integer pIdDepartamento) {
        dRepository.findById(pIdDepartamento).orElseThrow(() -> new ObjectNotFoundException("Departamento " + pIdDepartamento + " não existe"));

        try {
            dRepository.deleteById(pIdDepartamento);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegryViolationException("Departamento " + pIdDepartamento + " não pode ser excluído. Pois possui funcionários alocados!");
        }
    }
}
