package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.Plato;

import java.util.List;

public interface IPlatoPersistencePort {

    Plato savePlato(Plato plato);

    Plato getPlatoById(Long id);

    List<Plato> getAllPlato();

    List<Plato> findAllByRestauranteId(Long idRestaurante, Integer page, Integer size);

    void deletePlatoById(Long id);


}
