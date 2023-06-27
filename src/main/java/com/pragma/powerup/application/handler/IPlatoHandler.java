package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.PlatoRequestDTO;
import com.pragma.powerup.application.dto.request.PlatoRequestPutDTO;
import com.pragma.powerup.application.dto.response.PlatoResponseDTO;

import java.util.List;

public interface IPlatoHandler {

    void savePlato(PlatoRequestDTO platoRequestDTO);

    PlatoResponseDTO getPlatoById(Long id);

    List<PlatoResponseDTO> getAllPlatos();

    void deletePlatoById(Long id);

    void putPlato(Long id, PlatoRequestPutDTO platoRequestPutDTO);

    List<PlatoResponseDTO> findAllByRestauranteId(Long idRestaurante, Integer page, Integer size);

    void putEnableDiseablePlato(Long platoId, Long flag);
}
