package com.pragma.powerup.domain.api;


import com.pragma.powerup.domain.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant user);

    Restaurant getRestaurantById(Long id);

    Restaurant getRestaurantByIdPropietario(Long idpropietario);

    List<Restaurant> getRestaurantsWithPagination(Integer page, Integer size);

    List<Restaurant> getAllRestaurant();

    void deleteRestaurantById(Long id);
}
