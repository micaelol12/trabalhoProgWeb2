package com.mlconti.demo.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Cliente implements Serializable {

    public Cliente() {
    }

    public Cliente(Integer cd_cliente,
            @NotEmpty(message = "Campo nm_cliente não pode ser vazio!") @Length(min = 5, max = 100, message = "Campo nm_cliente deve ter entre 5 e 100 caracteres") String nm_cliente,
            @DecimalMin(value = "0.0", message = "Campo vl_limite_cred não pode ser vazio!") Double vl_limite_cred,
            @NotEmpty(message = "Campo ds_email não pode ser vazio!") @Length(min = 5, max = 50, message = "Campo ds_email deve ter entre 5 e 50 caracteres") String ds_email,
            @NotEmpty(message = "Campo nr_telefone não pode ser vazio!") @Length(min = 5, max = 15, message = "Campo nr_telefone deve ter entre 5 e 15 caracteres") String nr_telefone,
            Endereco endereco) {
        this.cd_cliente = cd_cliente;
        this.nm_cliente = nm_cliente;
        this.vl_limite_cred = vl_limite_cred;
        this.ds_email = ds_email;
        this.nr_telefone = nr_telefone;
        this.endereco = endereco;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cd_cliente;
    @NotEmpty(message = "Campo nm_cliente não pode ser vazio!")
    @Length(min = 5, max = 100, message = "Campo nm_cliente deve ter entre 5 e 100 caracteres")
    String nm_cliente;
    @DecimalMin(value = "0.0", message = "Campo vl_limite_cred não pode ser vazio!")
    Double vl_limite_cred;
    @NotEmpty(message = "Campo ds_email não pode ser vazio!")
    @Length(min = 5, max = 50, message = "Campo ds_email deve ter entre 5 e 50 caracteres")
    String ds_email;
    @NotEmpty(message = "Campo nr_telefone não pode ser vazio!")
    @Length(min = 5, max = 15, message = "Campo nr_telefone deve ter entre 5 e 15 caracteres")
    String nr_telefone;
    @ManyToOne
    @JoinColumn(name = "cd_endereco")
    Endereco endereco;

    public Integer getCd_cliente() {
        return cd_cliente;
    }

    public void setCd_cliente(Integer cd_cliente) {
        this.cd_cliente = cd_cliente;
    }

    public String getNm_cliente() {
        return nm_cliente;
    }

    public void setNm_cliente(String nm_cliente) {
        this.nm_cliente = nm_cliente;
    }

    public Double getVl_limite_cred() {
        return vl_limite_cred;
    }

    public void setVl_limite_cred(Double vl_limite_cred) {
        this.vl_limite_cred = vl_limite_cred;
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

    public String getNr_telefone() {
        return nr_telefone;
    }

    public void setNr_telefone(String nr_telefone) {
        this.nr_telefone = nr_telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cd_cliente == null) ? 0 : cd_cliente.hashCode());
        result = prime * result + ((nm_cliente == null) ? 0 : nm_cliente.hashCode());
        result = prime * result + ((vl_limite_cred == null) ? 0 : vl_limite_cred.hashCode());
        result = prime * result + ((ds_email == null) ? 0 : ds_email.hashCode());
        result = prime * result + ((nr_telefone == null) ? 0 : nr_telefone.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
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
        Cliente other = (Cliente) obj;
        if (cd_cliente == null) {
            if (other.cd_cliente != null)
                return false;
        } else if (!cd_cliente.equals(other.cd_cliente))
            return false;
        if (nm_cliente == null) {
            if (other.nm_cliente != null)
                return false;
        } else if (!nm_cliente.equals(other.nm_cliente))
            return false;
        if (vl_limite_cred == null) {
            if (other.vl_limite_cred != null)
                return false;
        } else if (!vl_limite_cred.equals(other.vl_limite_cred))
            return false;
        if (ds_email == null) {
            if (other.ds_email != null)
                return false;
        } else if (!ds_email.equals(other.ds_email))
            return false;
        if (nr_telefone == null) {
            if (other.nr_telefone != null)
                return false;
        } else if (!nr_telefone.equals(other.nr_telefone))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        return true;
    }

}
