package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.Categoria;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrdenPlatoResponseDTO {

    private Long id;
    private String nombre;
    private String precio;
    private String descripcion;
    private String urlImagen;
    private Categoria categoriaId;

    private String cantidad;

}
