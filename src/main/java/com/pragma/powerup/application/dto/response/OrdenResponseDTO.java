package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrdenResponseDTO {
    private Long id;
    private Long idCliente;
    private Long idChef;
    private Date fecha;
    private List<OrdenPlatoResponseDTO> pedidoPlatos;
}
