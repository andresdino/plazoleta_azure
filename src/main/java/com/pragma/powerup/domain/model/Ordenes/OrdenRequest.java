package com.pragma.powerup.domain.model.Ordenes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class OrdenRequest {
    private List<OrdenPlatoRequest> platos;
    private Long  resturanteId;


    public OrdenRequest() {
    }

    public OrdenRequest(List<OrdenPlatoRequest> platos, Long resturanteId) {
        this.platos = platos;
        this.resturanteId = resturanteId;
    }

    public List<OrdenPlatoRequest> getPlatos() {
        return platos;
    }

    public void setPlatos(List<OrdenPlatoRequest> platos) {
        this.platos = platos;
    }

    public Long getResturanteId() {
        return resturanteId;
    }

    public void setResturanteId(Long resturanteId) {
        this.resturanteId = resturanteId;
    }
}
