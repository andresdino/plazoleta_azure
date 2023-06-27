package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantUseCaseTest {

    @Mock
    IRestaurantPersistencePort iRestaurantPersistencePortMock;

    @InjectMocks
    Restaurant restaurant = new Restaurant();

    @InjectMocks
    RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        restaurant.setNombre("el solar");
        restaurant.setNit("1");
        restaurant.setDireccion("calle falsa 123");
        restaurant.setTelefono("1234567890");
        restaurant.setUrlLogo("img.jgp");
        restaurant.setIdPropietario(1L);
        Mockito.when(iRestaurantPersistencePortMock.getRestaurantById(1L)).thenReturn(restaurant);

        List<Restaurant> clasRestaurantList = new ArrayList<>();

        clasRestaurantList.add(restaurant);
        Mockito.when(iRestaurantPersistencePortMock.getAllRestaurants()).thenReturn(clasRestaurantList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRestaurantById() {
        var data = restaurantUseCase.getRestaurantById(1L);
    }

    @Test
    void getAllRestaurant() {
        var data = restaurantUseCase.getAllRestaurant();
    }
}