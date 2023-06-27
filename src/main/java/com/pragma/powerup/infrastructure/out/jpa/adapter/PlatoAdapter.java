package com.pragma.powerup.infrastructure.out.jpa.adapter;


import com.pragma.powerup.domain.model.Plato;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatoEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IPlatoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IPlatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlatoAdapter implements IPlatoPersistencePort {

    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;


    @Override
    public Plato savePlato(Plato plato) {
        PlatoEntity platoEntity = platoRepository.save(platoEntityMapper.toEntity(plato));
        return platoEntityMapper.toPlato(platoEntity);
    }

    @Override
    public Plato getPlatoById(Long id) {
        Optional<PlatoEntity> optionalPlatoEntity = platoRepository.findById(id);
        PlatoEntity platoEntity = optionalPlatoEntity.orElse(null);
        return platoEntityMapper.toPlato(platoEntity);
    }

    @Override
    public List<Plato> getAllPlato() {
        List<PlatoEntity> platoEntityList = platoRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
        if(platoEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return platoEntityMapper.toPlatolList(platoEntityList);
    }

    @Override
    public List<Plato> findAllByRestauranteId(Long idRestaurante, Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by("categoriaId"));
        return platoRepository.findAllByRestaurantId(idRestaurante, pageable)
                .stream()
                .map(platoEntityMapper::toPlato)
                .collect(Collectors.toList());
    }


    @Override
    public void deletePlatoById(Long id) {
            platoRepository.deleteById(id);
    }
}
