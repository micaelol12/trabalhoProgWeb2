package com.mlconti.demo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;

@Entity
public class Pedido implements Serializable {

    public Pedido(Integer nr_pedido,
            @DateTimeFormat(pattern = "dd/MM/yyyy") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dt_emissao,
            @DecimalMin(value = "0.0", message = "Campo vl_total não pode ser vazio!") Double vl_total,
            Cliente cliente) {
        this.nr_pedido = nr_pedido;
        this.dt_emissao = dt_emissao;
        this.vl_total = vl_total;
        this.cliente = cliente;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer nr_pedido;
    @DateTimeFormat(pattern = "dd/MMyyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dt_emissao;
    @DecimalMin(value = "0.0", message = "Campo vl_total não pode ser vazio!")
    Double vl_total;
    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    List<Item_pedido> item_pedido;

    public Pedido() {
    }

    public Integer getNr_pedido() {
        return nr_pedido;
    }

    public void setNr_pedido(Integer nr_pedido) {
        this.nr_pedido = nr_pedido;
    }

    public LocalDate getDt_emissao() {
        return dt_emissao;
    }

    public void setDt_emissao(LocalDate dt_emissao) {
        this.dt_emissao = dt_emissao;
    }

    public Double getVl_total() {
        return vl_total;
    }

    public void setVl_total(Double vl_total) {
        this.vl_total = vl_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nr_pedido == null) ? 0 : nr_pedido.hashCode());
        result = prime * result + ((dt_emissao == null) ? 0 : dt_emissao.hashCode());
        result = prime * result + ((vl_total == null) ? 0 : vl_total.hashCode());
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
        Pedido other = (Pedido) obj;
        if (nr_pedido == null) {
            if (other.nr_pedido != null)
                return false;
        } else if (!nr_pedido.equals(other.nr_pedido))
            return false;
        if (dt_emissao == null) {
            if (other.dt_emissao != null)
                return false;
        } else if (!dt_emissao.equals(other.dt_emissao))
            return false;
        if (vl_total == null) {
            if (other.vl_total != null)
                return false;
        } else if (!vl_total.equals(other.vl_total))
            return false;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        return true;
    }
}
