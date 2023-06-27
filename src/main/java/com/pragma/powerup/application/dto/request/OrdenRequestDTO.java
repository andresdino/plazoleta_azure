package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class OrdenRequestDTO {

    @NotNull(message = "La lista de platos no puede ser nula")
    @Size(min = 1, message = "Debe haber al menos un plato en la lista")
    private List<OrdenDishRequestDTO> platos;
    @NotNull(message = "El identificador del restaurante no puede ser nulo")
    private Long  resturanteId;
}
