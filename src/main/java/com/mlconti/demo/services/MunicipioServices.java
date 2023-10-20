package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.domain.Municipio;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.MunicipioRepository;

@Service
public class MunicipioServices {

    @Autowired
    private MunicipioRepository municipioRepository;

    public Municipio updateMunicipio(Municipio pMunicipio, Integer pId) {
        Municipio municipioAtual = municipioRepository.findById(pId).orElseThrow(
                () -> new ObjectNotFoundException("Municipio  " + pId + " não encontrado"));

        municipioAtual.setNm_localidade(pMunicipio.getNm_localidade());
        municipioAtual.setSg_uf(pMunicipio.getSg_uf());

        return municipioRepository.save(municipioAtual);
    }

    public ResponseEntity<DeleteError> deleteMunicipio(Integer pIdMunicipio) {
        Optional<Municipio> municipio = municipioRepository.findById(pIdMunicipio);
        DeleteError mensagem = new DeleteError();

        if (municipio.isEmpty()) {
            mensagem.setMensagem("Municipio com o id " + pIdMunicipio + " não encontrada");
            mensagem.setType("ERRO");

            return ResponseEntity.badRequest().body(mensagem);
        }

        try {
            municipioRepository.deleteById(pIdMunicipio);

        } catch (DataIntegrityViolationException e) {
            mensagem.setMensagem(
                    "Municipio " + pIdMunicipio + " não pode ser excluído. Pois possui endereços alocados!");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        mensagem.setMensagem("OK");
        mensagem.setType("");
        return ResponseEntity.ok().body(mensagem);
    }
}
