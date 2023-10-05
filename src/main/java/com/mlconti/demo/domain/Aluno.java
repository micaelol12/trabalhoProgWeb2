package com.mlconti.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Aluno implements Serializable {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_aluno == null) ? 0 : id_aluno.hashCode());
        result = prime * result + ((nm_aluno == null) ? 0 : nm_aluno.hashCode());
        result = prime * result + ((aulas == null) ? 0 : aulas.hashCode());
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
        Aluno other = (Aluno) obj;
        if (id_aluno == null) {
            if (other.id_aluno != null)
                return false;
        } else if (!id_aluno.equals(other.id_aluno))
            return false;
        if (nm_aluno == null) {
            if (other.nm_aluno != null)
                return false;
        } else if (!nm_aluno.equals(other.nm_aluno))
            return false;
        if (aulas == null) {
            if (other.aulas != null)
                return false;
        } else if (!aulas.equals(other.aulas))
            return false;
        return true;
    }

    public Aluno() {
    }

    public Aluno(Integer id_aluno, @NotEmpty String nm_aluno, List<Aula> aulas) {
        this.id_aluno = id_aluno;
        this.nm_aluno = nm_aluno;
        this.aulas = aulas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_aluno;

    @NotEmpty
    private String nm_aluno;

    @ManyToMany(mappedBy = "alunos")

    @JsonIgnoreProperties("alunos")
    private List<Aula> aulas = new ArrayList<>();

    public Integer getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Integer id_aluno) {
        this.id_aluno = id_aluno;
    }

    public String getNm_aluno() {
        return nm_aluno;
    }

    public void setNm_aluno(String nm_aluno) {
        this.nm_aluno = nm_aluno;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public void addAula(Aula a) {
        this.aulas.add(a);
    }

    public void removeAula(Aula a) {
        this.aulas.remove(a);
    }

}
