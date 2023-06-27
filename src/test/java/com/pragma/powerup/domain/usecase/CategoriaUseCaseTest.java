package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.spi.persistence.ICategoriaPersistencePort;
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
class CategoriaUseCaseTest {


    @InjectMocks
    Categoria categoria;

    @InjectMocks
    CategoriaUseCase categoriaUseCase;

    @Mock
    ICategoriaPersistencePort iCategoriaPersistencePortMock;

    Categoria categoriaObj = new Categoria();
    @BeforeEach
    void setUp() {

        categoriaObj.setNombre("Restaurante");
        categoriaObj.setDescripcion("Restaurante dedicado a corrientes platos a la carta");
        Mockito.when(iCategoriaPersistencePortMock.getCategoriaById(1L)).thenReturn(categoriaObj);

        List<Categoria> clasCategoriaList = new ArrayList<>();
        clasCategoriaList.add(categoriaObj);
        Mockito.when(iCategoriaPersistencePortMock.getAllCategoria()).thenReturn(clasCategoriaList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCateoriaById() {
        var data = categoriaUseCase.getCateoriaById(1L);
    }

    @Test
    void getAllCategoria() {
        var data = categoriaUseCase.getAllCategoria();
    }
}