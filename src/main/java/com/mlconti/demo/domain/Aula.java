package com.mlconti.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Aula implements Serializable {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_aula == null) ? 0 : id_aula.hashCode());
        result = prime * result + ((nm_aula == null) ? 0 : nm_aula.hashCode());
        result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
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
        Aula other = (Aula) obj;
        if (id_aula == null) {
            if (other.id_aula != null)
                return false;
        } else if (!id_aula.equals(other.id_aula))
            return false;
        if (nm_aula == null) {
            if (other.nm_aula != null)
                return false;
        } else if (!nm_aula.equals(other.nm_aula))
            return false;
        if (alunos == null) {
            if (other.alunos != null)
                return false;
        } else if (!alunos.equals(other.alunos))
            return false;
        return true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_aula;
    @NotEmpty
    private String nm_aula;

   @JsonIgnoreProperties("aulas")
    @ManyToMany
    @JoinTable(name = "aula_aluno", joinColumns = @JoinColumn(name = "id_aula"), inverseJoinColumns = @JoinColumn(name = "id_aluno"), uniqueConstraints = @UniqueConstraint(name = "aula_aluno_PK", columnNames = {
            "id_aula", "id_aluno" }))
    private List<Aluno> alunos = new ArrayList<>();

    public Aula(Integer id_aula, @NotEmpty String nm_aula, List<Aluno> alunos) {
        this.id_aula = id_aula;
        this.nm_aula = nm_aula;
        this.alunos = alunos;
    }

    public Aula() {
    }

    public Integer getId_aula() {
        return id_aula;
    }

    public void setId_aula(Integer id_aula) {
        this.id_aula = id_aula;
    }

    public String getNm_aula() {
        return nm_aula;
    }

    public void setNm_aula(String nm_aula) {
        this.nm_aula = nm_aula;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addAluno(Aluno a) {
        this.alunos.add(a);
    }

    public void removeAluno(Aluno a) {
        this.alunos.add(a);
    }

}
