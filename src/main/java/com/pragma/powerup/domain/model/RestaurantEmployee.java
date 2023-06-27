package com.pragma.powerup.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class RestaurantEmployee {

    private Long id;

    private String restaurantId;

    private String personId;

    public RestaurantEmployee() {
    }

    public RestaurantEmployee(Long id, String restaurantId, String personId) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
