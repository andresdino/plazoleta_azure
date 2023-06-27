package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequestDTO;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDTO;
import com.pragma.powerup.application.dto.response.RestaurantResponseDTO;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDTO restaurantRequestDTO);

    RestaurantResponseDTO getRestaurantById(Long id);

    RestaurantResponseDTO getRestaurantByIdPropietario(Long id_propietario);

    List<RestaurantResponseDTO> getAllRestaurants();

    List<RestaurantPaginationResponseDTO> getRestaurantsWithPagination(Integer page, Integer size);

    void deleteRestaurantById(Long id);
}
