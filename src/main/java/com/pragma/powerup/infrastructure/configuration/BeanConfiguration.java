package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.*;
import com.pragma.powerup.domain.spi.IObjectPersistencePort;
import com.pragma.powerup.domain.spi.persistence.*;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
import com.pragma.powerup.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.powerup.domain.usecase.*;
import com.pragma.powerup.infrastructure.out.jpa.adapter.*;
import com.pragma.powerup.infrastructure.out.jpa.mapper.*;
import com.pragma.powerup.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;

    private  final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

    private final IOrdenRepository ordenRepository;
    private final IOrdenEntityMapper ordenEntityMapper;

    private  final IOrdenPlatoRepository ordenPlatoRepository;
    private final IOrdenPlatoEntityMapper ordenPlatoEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort() {
        return new ObjectUseCase(objectPersistencePort());
    }


    @Bean
    public IPlatoPersistencePort platoPersistencePort(){
        return new PlatoAdapter(platoRepository, platoEntityMapper);
    }

    @Bean
    public IPlatoServicePort platoServicePort(){
        return new PlatoUseCase(platoPersistencePort(), restaurantPersistencePort(), categoriaPersistencePort() /* token()*/);
    }


    @Bean
    public ICategoriaPersistencePort categoriaPersistencePort(){
        return new CategoriaAdapter(categoriaRepository, categoriaEntityMapper);
    }

    @Bean
    public ICategoriaServicePort categoriaServicePort(){return new CategoriaUseCase(categoriaPersistencePort());
    }


    private IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort() {
        return new RestaurantEmployeeJpaAdapter(restaurantEmployeeRepository, restaurantEmployeeEntityMapper);
    }

    @Bean
    public IRestaurantEmployeeServicePort restaurantEmployeeServicePort(){
        return new RestaurantEmployeeUseCase(restaurantEmployeePersistencePort());
    }

    @Bean
    public IOrdenPersistencePort orderPersistencePort(){
        return new OrderAdapter(ordenRepository, ordenEntityMapper, ordenPlatoRepository, ordenPlatoEntityMapper);
    }


    @Bean
    public IOrdenServicePort orderServicePort(){
        return new OrdenUseCase(orderPersistencePort(), restaurantPersistencePort(), platoPersistencePort(), restaurantEmployeePersistencePort());
    }

}