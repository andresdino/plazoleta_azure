package com.pragma.powerup.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class OrdenPlato {

    private Long id;
    private Orden pedido;
    private Plato plato;
    private String cantidad;

    public OrdenPlato() {
    }

    public OrdenPlato(Long id, Orden pedido, Plato plato, String cantidad) {
        this.id = id;
        this.pedido = pedido;
        this.plato = plato;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orden getPedido() {
        return pedido;
    }

    public void setPedido(Orden pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
