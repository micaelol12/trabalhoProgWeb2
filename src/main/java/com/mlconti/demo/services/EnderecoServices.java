package com.mlconti.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mlconti.demo.domain.DeleteError;
import com.mlconti.demo.domain.Endereco;
import com.mlconti.demo.domain.Municipio;
import com.mlconti.demo.exceptions.ObjectNotFoundException;
import com.mlconti.demo.repository.EnderecoRepository;
import com.mlconti.demo.repository.MunicipioRepository;

@Service
public class EnderecoServices {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    MunicipioRepository municipioRepository;

    public Endereco create(Endereco pEndereco, Integer pIdMunicipio) {
        Municipio municipioEndereco = municipioRepository.findById(pIdMunicipio)
                .orElseThrow(() -> new ObjectNotFoundException("Municipio " + pIdMunicipio + " não encontrado!"));

        pEndereco.setCd_endereco(null);
        pEndereco.setMunicipio(municipioEndereco);

        return enderecoRepository.save(pEndereco);
    }

     public Endereco updateEndereco(Integer eId, Endereco pEndereco, Integer mId) {
        Endereco enderecoAtual = enderecoRepository.findById(eId).orElseThrow(
                () -> new ObjectNotFoundException("Endereço  " + eId + " não encontrado"));

        Municipio novoMunicipio = municipioRepository.findById(mId).orElseThrow(
                () -> new ObjectNotFoundException("Municipio  " + mId + " não encontrada"));

        enderecoAtual.setDs_complemento(pEndereco.getDs_complemento());
        enderecoAtual.setDs_logradouro(pEndereco.getDs_logradouro());
        enderecoAtual.setNm_bairro(pEndereco.getNm_bairro());
        enderecoAtual.setNumero(pEndereco.getNumero());
        enderecoAtual.setNr_cep(pEndereco.getNr_cep());

        enderecoAtual.setMunicipio(novoMunicipio);

        return enderecoRepository.save(enderecoAtual);
    }

    public ResponseEntity<DeleteError> deleteEndereco(Integer pIdEndereco) {
        DeleteError mensagem = new DeleteError();
        Optional<Endereco> produto = enderecoRepository.findById(pIdEndereco);

        if (produto.isEmpty()) {
            mensagem.setMensagem("Endereço com o id " + pIdEndereco + " não encontrado");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        try {
            enderecoRepository.deleteById(pIdEndereco);

        } catch (DataIntegrityViolationException e) {
            mensagem.setMensagem(
                    "Endereco " + pIdEndereco + " não pode ser excluído.");
            mensagem.setType("ERRO");
            return ResponseEntity.badRequest().body(mensagem);
        }

        mensagem.setMensagem("OK");
        mensagem.setType("");
        return ResponseEntity.ok().body(mensagem);
    }
}
