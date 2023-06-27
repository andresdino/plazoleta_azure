package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Plato;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IPlatoServicePort {

    void savePlato(Plato plato);

    Plato getPlatoById(Long id);

    List<Plato> getAllPlatos();

    void deletePlatoById(Long id);

    short putPlato(Long id, Plato plato);

    void putEnableDisablePlato(Long platoId, Long flag);

    List<Plato> findAllByRestauranteId(Long idRestaurante, Integer page, Integer size);

}
