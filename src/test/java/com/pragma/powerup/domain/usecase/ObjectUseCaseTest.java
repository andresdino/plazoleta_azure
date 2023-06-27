package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.ObjectModel;
import com.pragma.powerup.domain.spi.IObjectPersistencePort;
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
class ObjectUseCaseTest {

    @Mock
    IObjectPersistencePort iObjectPersistencePortMock;

    @InjectMocks
    ObjectUseCase objectUseCase;

    @InjectMocks
    ObjectModel objectModel;

    @BeforeEach
    void setUp() {
        objectModel.setName("Granja burg");
        objectModel.setName("burgesia");
        List<ObjectModel> claObjecList = new ArrayList<>();
        claObjecList.add(objectModel);
        Mockito.when(iObjectPersistencePortMock.getAllObjects()).thenReturn(claObjecList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllObjects() {

        var data = objectUseCase.getAllObjects();
    }
}