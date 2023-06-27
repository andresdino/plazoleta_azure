package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.PlatoRequestDTO;
import com.pragma.powerup.domain.api.IPlatoServicePort;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Plato;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PlatoUseCaseTest {

    @Mock
    IPlatoPersistencePort iPlatoPersistencePortMock;

    @InjectMocks
    Plato dishCurrent = new Plato();

    @InjectMocks
    Plato modifyPlate = new Plato();
    @InjectMocks
    Categoria categoria;

    @InjectMocks
    Restaurant restaurant;

    @InjectMocks
    PlatoUseCase platoUseCase;



    @BeforeEach
    void setUp() {
        dishCurrent.setId(1L);
        dishCurrent.setNombre("Bandeja paisa");
        dishCurrent.setPrecio("20000");
        dishCurrent.setDescripcion("bandeja con sop y jugo");
        dishCurrent.setUrlImagen("");
        dishCurrent.setActivo(true);
        dishCurrent.setRestauranteId(restaurant);
        dishCurrent.setCategoriaId(categoria);
        Mockito.when(iPlatoPersistencePortMock.getPlatoById(1L)).thenReturn(dishCurrent);

        List<Plato> clasPlatoList = new ArrayList<>();

        clasPlatoList.add(dishCurrent);
        Mockito.when(iPlatoPersistencePortMock.getAllPlato()).thenReturn(clasPlatoList);
        platoUseCase.putPlato(1L, dishCurrent);

/*
        modifyPlate.setNombre("Plato nuevo");
        modifyPlate.setPrecio("30000");
        modifyPlate.setDescripcion("Sin sopa");

        Mockito.when(iPlatoPersistencePortMock.getPlatoById(dishCurrent.getId())).thenReturn(modifyPlate);
*/

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPlatoById() {
        var data = platoUseCase.getPlatoById(1L);
    }

    @Test
    void getAllPlatos() {var data = platoUseCase.getAllPlatos();
    }

    @Test
    void putPlato(){

        var data =  platoUseCase.putPlato(1L, dishCurrent);
        Assertions.assertEquals("bandeja con sop y jugo", dishCurrent.getDescripcion());

    }

    @Test
    void putEnableDisablePlato(){

       platoUseCase.putEnableDisablePlato(1L, 1L);
    }

}