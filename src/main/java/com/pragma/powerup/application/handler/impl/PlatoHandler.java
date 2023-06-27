package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.PlatoRequestDTO;
import com.pragma.powerup.application.dto.request.PlatoRequestPutDTO;
import com.pragma.powerup.application.dto.response.PlatoResponseDTO;
import com.pragma.powerup.application.handler.IPlatoHandler;
import com.pragma.powerup.application.mapper.request.IPlatoRequestMapper;
import com.pragma.powerup.application.mapper.response.IPlatoResponseMapper;
import com.pragma.powerup.domain.api.IPlatoServicePort;
import com.pragma.powerup.domain.model.Plato;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatoHandler implements IPlatoHandler {

    private final IPlatoServicePort platoServicePort;
    private final IPlatoRequestMapper platoRequestMapper;
    private final IPlatoResponseMapper platoResponseMapper;

    @Override
    public void savePlato(PlatoRequestDTO platoRequestDTO) {

        Plato plato = platoRequestMapper.toPlato(platoRequestDTO);
        platoServicePort.savePlato(plato);
    }

    @Override
    public PlatoResponseDTO getPlatoById(Long id) {
        Plato plato = platoServicePort.getPlatoById(id);
        return platoResponseMapper.toResponse(plato);
    }

    @Override
    public List<PlatoResponseDTO> getAllPlatos() {
        List<Plato> platoList = platoServicePort.getAllPlatos();
        if(platoList.isEmpty()){
            throw new NoDataFoundException();
        }
        return platoResponseMapper.toResponseList(platoList);
    }

    @Override
    public void deletePlatoById(Long id) {
                    platoServicePort.deletePlatoById(id);
    }

    @Override
    public void putPlato(Long id ,PlatoRequestPutDTO platoRequestPutDTO) {

        Plato plato = platoRequestMapper.toPutPlato(platoRequestPutDTO);
        platoServicePort.putPlato( id, plato);
    }

    public List<PlatoResponseDTO> findAllByRestauranteId(Long idRestaurante ,Integer page, Integer size){
        List<Plato> platoList = platoServicePort.findAllByRestauranteId(idRestaurante,page,size);
        if (platoList.isEmpty()){
            throw new NoDataFoundException();
        }
        return platoResponseMapper.toResponseList(platoList);
    }

    @Override
    public void putEnableDiseablePlato (Long platoId,Long flag){
        platoServicePort.putEnableDisablePlato(platoId,flag);
    }
}
