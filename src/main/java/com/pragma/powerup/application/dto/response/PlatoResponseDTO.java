package com.pragma.powerup.application.dto.response;


import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoResponseDTO {

    private Long id;
    private String nombre;
    private String precio;
    private String descripcion;
    private String urlImagen;
    private Boolean activo;
    private Restaurant restauranteId;
    private Categoria categoriaId;
}
