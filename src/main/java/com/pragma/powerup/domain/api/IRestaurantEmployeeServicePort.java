package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RestaurantEmployee;

import java.util.List;

public interface IRestaurantEmployeeServicePort {

    void saveRestaurantEmployee(RestaurantEmployee restaurantEmployeeModel);

    List<RestaurantEmployee> getAllRestaurantEmployees();
}
