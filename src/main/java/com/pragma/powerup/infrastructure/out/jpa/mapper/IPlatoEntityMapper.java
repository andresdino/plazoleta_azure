package com.pragma.powerup.infrastructure.out.jpa.mapper;


import com.pragma.powerup.domain.model.Plato;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoEntityMapper {


        PlatoEntity toEntity(Plato  plato);


        Plato toPlato(PlatoEntity platoEntity);

        List<Plato> toPlatolList(List<PlatoEntity> platoEntityList);


}
