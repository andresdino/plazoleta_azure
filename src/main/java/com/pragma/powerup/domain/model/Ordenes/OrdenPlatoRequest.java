package com.pragma.powerup.domain.model.Ordenes;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class OrdenPlatoRequest {

    private Long idPlatos;
    private Long cantidad;

    public OrdenPlatoRequest() {
    }

    public OrdenPlatoRequest(Long idPlatos, Long cantidad) {
        this.idPlatos = idPlatos;
        this.cantidad = cantidad;
    }

    public Long getIdPlatos() {
        return idPlatos;
    }

    public void setIdPlatos(Long idPlatos) {
        this.idPlatos = idPlatos;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
