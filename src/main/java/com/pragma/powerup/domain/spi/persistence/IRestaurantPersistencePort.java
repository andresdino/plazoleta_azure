package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {

    Restaurant saveRestaurant(Restaurant user);

    Restaurant getRestaurantById(Long id);

    Restaurant getRestaurantByIdPropietario(Long id_propietario);

    List<Restaurant> getRestaurantsWithPagination(Integer page, Integer size);

    List<Restaurant> getAllRestaurants();

    void deleteRestaurantById(Long id);
}
