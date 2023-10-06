package com.mlconti.demo.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Produto {

    public Produto() {
    }

    public Produto(Integer cd_produto,
            @NotEmpty(message = "Campo NM_PRODUTO não pode ser vazio!") @Length(min = 5, max = 255, message = "Campo NM_PRODUTO deve ter entre 5 e 255 caracteres") String nm_produto,
            @NotEmpty(message = "Campo QT_ESTOQUE não pode ser vazio!") @Range(min = 0, message = "A quantidade precisa ser maior ou igual a 0") Integer qt_estoque,
            @DecimalMin(value = "0.0", message = "Campo VL_PRODUTO não pode ser vazio!") Double vl_produto,
            Categoria categoria) {
        this.cd_produto = cd_produto;
        this.nm_produto = nm_produto;
        this.qt_estoque = qt_estoque;
        this.vl_produto = vl_produto;
        this.categoria = categoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_produto;
    @NotEmpty(message = "Campo NM_PRODUTO não pode ser vazio!")
    @Length(min = 5, max = 255, message = "Campo NM_PRODUTO deve ter entre 5 e 255 caracteres")
    private String nm_produto;
    @Range(min = 0, message = "A quantidade precisa ser maior ou igual a 0")
    private Integer qt_estoque;
    @DecimalMin(value = "0.0", message = "Campo VL_PRODUTO não pode ser vazio!")
    private Double vl_produto;
    @ManyToOne
    @JoinColumn(name = "cd_categoria")
    private Categoria categoria;

    public Integer getCd_produto() {
        return cd_produto;
    }

    public void setCd_produto(Integer cd_produto) {
        this.cd_produto = cd_produto;
    }

    public String getNm_produto() {
        return nm_produto;
    }

    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }

    public Integer getQt_estoque() {
        return qt_estoque;
    }

    public void setQt_estoque(Integer qt_estoque) {
        this.qt_estoque = qt_estoque;
    }

    public Double getVl_produto() {
        return vl_produto;
    }

    public void setVl_produto(Double vl_produto) {
        this.vl_produto = vl_produto;
    }

    public Categoria getCd_categoria() {
        return categoria;
    }

    public void setCd_categoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cd_produto == null) ? 0 : cd_produto.hashCode());
        result = prime * result + ((nm_produto == null) ? 0 : nm_produto.hashCode());
        result = prime * result + ((qt_estoque == null) ? 0 : qt_estoque.hashCode());
        result = prime * result + ((vl_produto == null) ? 0 : vl_produto.hashCode());
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
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
        Produto other = (Produto) obj;
        if (cd_produto == null) {
            if (other.cd_produto != null)
                return false;
        } else if (!cd_produto.equals(other.cd_produto))
            return false;
        if (nm_produto == null) {
            if (other.nm_produto != null)
                return false;
        } else if (!nm_produto.equals(other.nm_produto))
            return false;
        if (qt_estoque == null) {
            if (other.qt_estoque != null)
                return false;
        } else if (!qt_estoque.equals(other.qt_estoque))
            return false;
        if (vl_produto == null) {
            if (other.vl_produto != null)
                return false;
        } else if (!vl_produto.equals(other.vl_produto))
            return false;
        if (categoria == null) {
            if (other.categoria != null)
                return false;
        } else if (!categoria.equals(other.categoria))
            return false;
        return true;
    }

}
