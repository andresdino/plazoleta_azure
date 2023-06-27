package com.pragma.powerup.domain.model.Ordenes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class OrdenResponse {
    private Long id;
    private Long idCliente;
    private Long idChef;
    private Date fecha;
    private List<OrdenPlatoResponse> pedidoPlatos;

    public OrdenResponse() {
    }

    public OrdenResponse(Long id, Long idCliente, Long idChef, Date fecha, List<OrdenPlatoResponse> pedidoPlatos) {
        this.id = id;
        this.idCliente = idCliente;
        this.idChef = idChef;
        this.fecha = fecha;
        this.pedidoPlatos = pedidoPlatos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<OrdenPlatoResponse> getPedidoPlatos() {
        return pedidoPlatos;
    }

    public void setPedidoPlatos(List<OrdenPlatoResponse> pedidoPlatos) {
        this.pedidoPlatos = pedidoPlatos;
    }
}
