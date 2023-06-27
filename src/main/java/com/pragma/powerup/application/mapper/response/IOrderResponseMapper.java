package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.request.OrdenRequestDTO;
import com.pragma.powerup.application.dto.response.OrdenResponseDTO;
import com.pragma.powerup.domain.model.Orden;
import com.pragma.powerup.domain.model.Ordenes.OrdenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

    OrdenRequestDTO toResponse(Orden order);
    List<OrdenRequestDTO> toResponseList(List<Orden> order);
    List<OrdenResponseDTO> toOrdenResponseList(List<OrdenResponse> orderResponseModels);
}
