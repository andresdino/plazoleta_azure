package com.pragma.powerup.application.mapper.request;


import com.pragma.powerup.application.dto.request.OrdenRequestDTO;
import com.pragma.powerup.domain.model.Orden;
import com.pragma.powerup.domain.model.Ordenes.OrdenRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {

    Orden toOrden (OrdenRequestDTO ordenRequestDTO);

    OrdenRequest toOrdenRequest(OrdenRequestDTO ordenRequestDTO);

}
