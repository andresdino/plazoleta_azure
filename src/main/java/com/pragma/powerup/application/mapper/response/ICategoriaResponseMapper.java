package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.response.CategoriaResponseDTO;
import com.pragma.powerup.domain.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ICategoriaResponseMapper {

    CategoriaResponseDTO toResponse(Categoria categoria);

    List<CategoriaResponseDTO> toResponseList(List<Categoria> categoriaList);
}
