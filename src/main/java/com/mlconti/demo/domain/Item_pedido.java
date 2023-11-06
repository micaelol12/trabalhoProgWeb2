package com.mlconti.demo.domain;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;

@Entity
public class Item_pedido {

    public Item_pedido(Integer cd_itemProduto,
            @Range(min = 0, message = "A quantidade precisa ser maior do que 0") Integer qt_produto,
            @DecimalMin(value = "0.0", message = "O valor tem que ser maior do que 0") Double vl_produto, Pedido pedido,
            Produto produto) {
        this.cd_itemProduto = cd_itemProduto;
        this.qt_produto = qt_produto;
        this.vl_produto = vl_produto;
        this.pedido = pedido;
        this.produto = produto;
    }

    public Item_pedido() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_itemProduto;
    @Range(min = 0, message = "A quantidade precisa ser maior do que 0")
    private Integer qt_produto;
    @DecimalMin(value = "0.0", message = "O valor tem que ser maior do que 0")
    private Double vl_produto;

    @ManyToOne
    @JoinColumn(name = "nr_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cd_produto")
    private Produto produto;

    public Integer getQt_produto() {
        return qt_produto;
    }

    public void setQt_produto(Integer qt_produto) {
        this.qt_produto = qt_produto;
    }

    public Double getVl_produto() {
        return vl_produto;
    }

    public void setVl_produto(Double vl_produto) {
        this.vl_produto = vl_produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getCd_itemProduto() {
        return cd_itemProduto;
    }

    public void setCd_itemProduto(Integer cd_itemProduto) {
        this.cd_itemProduto = cd_itemProduto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cd_itemProduto == null) ? 0 : cd_itemProduto.hashCode());
        result = prime * result + ((qt_produto == null) ? 0 : qt_produto.hashCode());
        result = prime * result + ((vl_produto == null) ? 0 : vl_produto.hashCode());
        result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
        result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
        Item_pedido other = (Item_pedido) obj;
        if (cd_itemProduto == null) {
            if (other.cd_itemProduto != null)
                return false;
        } else if (!cd_itemProduto.equals(other.cd_itemProduto))
            return false;
        if (qt_produto == null) {
            if (other.qt_produto != null)
                return false;
        } else if (!qt_produto.equals(other.qt_produto))
            return false;
        if (vl_produto == null) {
            if (other.vl_produto != null)
                return false;
        } else if (!vl_produto.equals(other.vl_produto))
            return false;
        if (pedido == null) {
            if (other.pedido != null)
                return false;
        } else if (!pedido.equals(other.pedido))
            return false;
        if (produto == null) {
            if (other.produto != null)
                return false;
        } else if (!produto.equals(other.produto))
            return false;
        return true;
    }

}
