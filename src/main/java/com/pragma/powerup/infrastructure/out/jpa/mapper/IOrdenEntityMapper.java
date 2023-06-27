package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Orden;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrdenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrdenEntityMapper {

    OrdenEntity toEntity(Orden orderModel);
    Orden toOrden(OrdenEntity ordenEntity);

    List<Orden> toOrderModelList(List<OrdenEntity> orderEntities);
}
