package com.pragma.powerup.application.mapper.request;


import com.pragma.powerup.application.dto.request.RestaurantRequestDTO;
import com.pragma.powerup.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {

    Restaurant toRestaurant(RestaurantRequestDTO restaurantRequestDTO);
}
