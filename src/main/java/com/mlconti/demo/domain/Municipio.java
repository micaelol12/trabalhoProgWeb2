package com.mlconti.demo.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Municipio  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_municipio;
    @Length(min = 5, max = 255, message = "Campo nm_localidade deve ter entre 2 e 40 caracteres")
    private String nm_localidade;
    @Length(min = 2, max = 2, message = "Campo sg_uf deve ter entre2 caracteres")
    private String sg_uf;

    public Municipio(Integer cd_municipio,
            @Length(min = 5, max = 255, message = "Campo nm_localidade deve ter entre 2 e 40 caracteres") String nm_localidade,
            @Length(min = 2, max = 2, message = "Campo sg_uf deve ter entre2 caracteres") String sg_uf) {
        this.cd_municipio = cd_municipio;
        this.nm_localidade = nm_localidade;
        this.sg_uf = sg_uf;
    }

    public Municipio() {
    }

    public Integer getCd_municipio() {
        return cd_municipio;
    }

    public void setCd_municipio(Integer cd_municipio) {
        this.cd_municipio = cd_municipio;
    }

    public String getNm_localidade() {
        return nm_localidade;
    }

    public void setNm_localidade(String nm_localidade) {
        this.nm_localidade = nm_localidade;
    }

    public String getSg_uf() {
        return sg_uf;
    }

    public void setSg_uf(String sg_uf) {
        this.sg_uf = sg_uf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cd_municipio == null) ? 0 : cd_municipio.hashCode());
        result = prime * result + ((nm_localidade == null) ? 0 : nm_localidade.hashCode());
        result = prime * result + ((sg_uf == null) ? 0 : sg_uf.hashCode());
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
        Municipio other = (Municipio) obj;
        if (cd_municipio == null) {
            if (other.cd_municipio != null)
                return false;
        } else if (!cd_municipio.equals(other.cd_municipio))
            return false;
        if (nm_localidade == null) {
            if (other.nm_localidade != null)
                return false;
        } else if (!nm_localidade.equals(other.nm_localidade))
            return false;
        if (sg_uf == null) {
            if (other.sg_uf != null)
                return false;
        } else if (!sg_uf.equals(other.sg_uf))
            return false;
        return true;
    }
}
