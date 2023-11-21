package com.mlconti.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mlconti.demo.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

  @Query("SELECT objeto_endereco FROM Endereco objeto_endereco WHERE objeto_endereco.municipio.cd_municipio = :pIdMunicipio ORDER BY ds_logradouro")
    List<Endereco> findByMunicipio(@Param(value="pIdMunicipio") Integer pIdMunicipio);
}
