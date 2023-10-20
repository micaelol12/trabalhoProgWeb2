package com.mlconti.demo.domain;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cd_endereco;
    @NotEmpty(message = "Campo ds_logradouro não pode ser vazio!")
    @Length(min = 5, max = 100, message = "Campo ds_logradouro deve ter entre 5 e 100 caracteres")
    String ds_logradouro;
    @Length(min = 5, max = 100, message = "Campo ds_complemento deve ter entre 5 e 100 caracteres")
    String ds_complemento;
    @NotEmpty(message = "Campo nm_bairro não pode ser vazio!")
    @Length(min = 5, max = 100, message = "Campo nm_bairro deve ter entre 5 e 100 caracteres")
    String nm_bairro;
    @NotNull(message = "Campo numero não pode ser vazio!")
    Integer numero;
    @NotEmpty(message = "Campo nr_cep não pode ser vazio!")
    @Length(min = 8, max = 8, message = "Campo nr_cep deve ter 8 caracteres")
    String nr_cep;
    @ManyToOne
    @JoinColumn(name = "cd_municipio")
    Municipio municipio;

    public Endereco() {
    }

    public Endereco(Integer cd_endereco,
            @NotEmpty(message = "Campo ds_logradouro não pode ser vazio!") @Length(min = 5, max = 100, message = "Campo ds_logradouro deve ter entre 5 e 100 caracteres") String ds_logradouro,
            @Length(min = 5, max = 100, message = "Campo ds_complemento deve ter entre 5 e 100 caracteres") String ds_complemento,
            @NotEmpty(message = "Campo nm_bairro não pode ser vazio!") @Length(min = 5, max = 100, message = "Campo nm_bairro deve ter entre 5 e 100 caracteres") String nm_bairro,
            @NotEmpty(message = "Campo numero não pode ser vazio!") Integer numero,
            @NotEmpty(message = "Campo nr_cep não pode ser vazio!") @Length(min = 8, max = 8, message = "Campo nr_cep deve ter 8 caracteres") String nr_cep,
            Municipio municipio) {
        this.cd_endereco = cd_endereco;
        this.ds_logradouro = ds_logradouro;
        this.ds_complemento = ds_complemento;
        this.nm_bairro = nm_bairro;
        this.numero = numero;
        this.nr_cep = nr_cep;
        this.municipio = municipio;
    }

    public Integer getCd_endereco() {
        return cd_endereco;
    }

    public void setCd_endereco(Integer cd_endereco) {
        this.cd_endereco = cd_endereco;
    }

    public String getDs_logradouro() {
        return ds_logradouro;
    }

    public void setDs_logradouro(String ds_logradouro) {
        this.ds_logradouro = ds_logradouro;
    }

    public String getDs_complemento() {
        return ds_complemento;
    }

    public void setDs_complemento(String ds_complemento) {
        this.ds_complemento = ds_complemento;
    }

    public String getNm_bairro() {
        return nm_bairro;
    }

    public void setNm_bairro(String nm_bairro) {
        this.nm_bairro = nm_bairro;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public String getNr_cep() {
        return nr_cep;
    }

    public void setNr_cep(String nr_cep) {
        this.nr_cep = nr_cep;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cd_endereco == null) ? 0 : cd_endereco.hashCode());
        result = prime * result + ((ds_logradouro == null) ? 0 : ds_logradouro.hashCode());
        result = prime * result + ((ds_complemento == null) ? 0 : ds_complemento.hashCode());
        result = prime * result + ((nm_bairro == null) ? 0 : nm_bairro.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + ((nr_cep == null) ? 0 : nr_cep.hashCode());
        result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        if (cd_endereco == null) {
            if (other.cd_endereco != null)
                return false;
        } else if (!cd_endereco.equals(other.cd_endereco))
            return false;
        if (ds_logradouro == null) {
            if (other.ds_logradouro != null)
                return false;
        } else if (!ds_logradouro.equals(other.ds_logradouro))
            return false;
        if (ds_complemento == null) {
            if (other.ds_complemento != null)
                return false;
        } else if (!ds_complemento.equals(other.ds_complemento))
            return false;
        if (nm_bairro == null) {
            if (other.nm_bairro != null)
                return false;
        } else if (!nm_bairro.equals(other.nm_bairro))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        if (nr_cep == null) {
            if (other.nr_cep != null)
                return false;
        } else if (!nr_cep.equals(other.nr_cep))
            return false;
        if (municipio == null) {
            if (other.municipio != null)
                return false;
        } else if (!municipio.equals(other.municipio))
            return false;
        return true;
    }
};