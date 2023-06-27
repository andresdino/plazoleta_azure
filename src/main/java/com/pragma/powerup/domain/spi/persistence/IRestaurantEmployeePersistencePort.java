package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.RestaurantEmployee;

import java.util.List;

public interface IRestaurantEmployeePersistencePort {


    RestaurantEmployee findByPersonId(String idEmpleado);
    RestaurantEmployee saveRestaurantEmployee(RestaurantEmployee restaurantEmployeeModel);

    List<RestaurantEmployee> getAllRestaurantEmployees();
}
