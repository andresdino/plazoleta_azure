package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RestaurantEmployeeRequestDto {

    @NotBlank(message = "se requiere el restaurante_id")
    private String restaurantId;

    @NotBlank(message = "se requiere el empleado_id")
    private String personId;
}
