package com.pragma.powerup.application.mapper.request;


import com.pragma.powerup.application.dto.request.CategoriaRequestDTO;
import com.pragma.powerup.domain.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoriaRequestMapper {

    Categoria toCategoria(CategoriaRequestDTO CategoriaRequestDTO);
}
