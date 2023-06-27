package com.pragma.powerup.application.mapper.request;

import com.pragma.powerup.application.dto.request.PlatoRequestDTO;
import com.pragma.powerup.application.dto.request.PlatoRequestPutDTO;
import com.pragma.powerup.domain.model.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoRequestMapper {

    @Mapping(target = "restauranteId.id", source = "restaurantId")
    @Mapping(target = "categoriaId.id", source = "categoriaId")
    Plato toPlato(PlatoRequestDTO platoRequestDTO);


    Plato toPutPlato(PlatoRequestPutDTO platoRequestPutDTO);






}
