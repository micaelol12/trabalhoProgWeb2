package com.mlconti.demo.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_funcionario;

    @NotEmpty(message = "Campo NM_FUNCIONARIO não pode ser vazio!")
    @Length(min = 5, max = 255, message = "Campo NM_FUNCIONARIO deve ter entre 5 e 255 caracteres")
    private String nm_funcionario;

    @ManyToOne
    @JoinColumn(name = "id_depto")
    private Departamento departamento_pai;

    public Funcionario(Integer id_funcionario,
            @NotEmpty(message = "Campo NM_FUNCIONARIO não pode ser vazio!") @Length(min = 5, max = 255, message = "Campo NM_FUNCIONARIO deve ter entre 5 e 255 caracteres") String nm_funcionario,
            Departamento departamento_pai) {
        this.id_funcionario = id_funcionario;
        this.nm_funcionario = nm_funcionario;
        this.departamento_pai = departamento_pai;
    }

    public Funcionario() {
    }

    public Departamento getDepartamento_pai() {
        return departamento_pai;
    }

    public Integer getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNm_funcionario() {
        return nm_funcionario;
    }

    public void setNm_funcionario(String nm_funcionario) {
        this.nm_funcionario = nm_funcionario;
    }

    public void setDepartamento_pai(Departamento departamento_pai) {
        this.departamento_pai = departamento_pai;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_funcionario == null) ? 0 : id_funcionario.hashCode());
        result = prime * result + ((nm_funcionario == null) ? 0 : nm_funcionario.hashCode());
        result = prime * result + ((departamento_pai == null) ? 0 : departamento_pai.hashCode());
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
        Funcionario other = (Funcionario) obj;
        if (id_funcionario == null) {
            if (other.id_funcionario != null)
                return false;
        } else if (!id_funcionario.equals(other.id_funcionario))
            return false;
        if (nm_funcionario == null) {
            if (other.nm_funcionario != null)
                return false;
        } else if (!nm_funcionario.equals(other.nm_funcionario))
            return false;
        if (departamento_pai == null) {
            if (other.departamento_pai != null)
                return false;
        } else if (!departamento_pai.equals(other.departamento_pai))
            return false;
        return true;
    }

}
