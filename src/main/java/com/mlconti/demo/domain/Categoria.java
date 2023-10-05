package com.mlconti.demo.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_categoria;

    public Integer getCd_categoria() {
        return cd_categoria;
    }

    public void setCd_categoria(Integer cd_categoria) {
        this.cd_categoria = cd_categoria;
    }

    @NotEmpty
    private String ds_categoria;

    public String getDs_categoria() {
        return ds_categoria;
    }

    public void setDs_categoria(String ds_categoria) {
        this.ds_categoria = ds_categoria;
    }

    public Categoria(Integer cd_categoria, @NotEmpty String ds_categoria) {
        this.cd_categoria = cd_categoria;
        this.ds_categoria = ds_categoria;
    }

    public Categoria() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cd_categoria == null) ? 0 : cd_categoria.hashCode());
        result = prime * result + ((ds_categoria == null) ? 0 : ds_categoria.hashCode());
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
        Categoria other = (Categoria) obj;
        if (cd_categoria == null) {
            if (other.cd_categoria != null)
                return false;
        } else if (!cd_categoria.equals(other.cd_categoria))
            return false;
        if (ds_categoria == null) {
            if (other.ds_categoria != null)
                return false;
        } else if (!ds_categoria.equals(other.ds_categoria))
            return false;
        return true;
    }



}
