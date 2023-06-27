package com.pragma.powerup.application.mapper.response;


import com.pragma.powerup.application.dto.response.PlatoResponseDTO;
import com.pragma.powerup.domain.model.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoResponseMapper {


    PlatoResponseDTO toResponse(Plato plato);

    List<PlatoResponseDTO> toResponseList(List<Plato> platoList);
}
