package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrdenPlato;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrdenPlatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrdenPlatoEntityMapper {


    OrdenPlatoEntity toEntity(OrdenPlato orderDishModel);
    OrdenPlato toOrderModel(OrdenPlatoEntity orderDishEntity);

    List<OrdenPlato> toOrderPlatoModelList(List<OrdenPlatoEntity> orderDishEntities);
}
