package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Orden;
import com.pragma.powerup.domain.model.OrdenPlato;
import com.pragma.powerup.domain.spi.persistence.IOrdenPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrdenEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrdenPlatoEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrdenEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrdenPlatoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrdenPlatoRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
public class OrderAdapter implements IOrdenPersistencePort {

    private final IOrdenRepository ordenRepository;

    private final IOrdenEntityMapper ordenEntityMapper;

    private final IOrdenPlatoRepository ordenPlatoRepository;

    private final IOrdenPlatoEntityMapper OrdenPlatoEntityMapper;
    @Override
    public Orden saveOrden(Orden orden) {
        OrdenEntity ordenEntity= ordenRepository.save(ordenEntityMapper.toEntity(orden));
        return ordenEntityMapper.toOrden(ordenEntity);
    }

    @Override
    public void saveOrdenPlato(List<OrdenPlato> ordenPlatos) {
        List<OrdenPlatoEntity> ordenPlatoEntities = new ArrayList<>();
        for (int i=0; i<ordenPlatos.size();i++){
            ordenPlatoEntities.add(OrdenPlatoEntityMapper.toEntity(ordenPlatos.get(i)));
        }
        ordenPlatoRepository.saveAll(ordenPlatoEntities);
    }


    @Override
    public List<Orden> getAllOrdenesWithPagination(Integer page, Integer size, Long restauranteId, String estado) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<OrdenEntity> ordenEntityPage= ordenRepository.findByRestaurante_idAndEstado(restauranteId,estado, pageable);
        if(ordenEntityPage.isEmpty()){
            throw new NoDataFoundException();
        }
        return ordenEntityPage
                .stream().map(ordenEntityMapper::toOrden).collect(Collectors.toList());

    }

    @Override
    public List<OrdenPlato> getAllOrdenessByPedido(Long pedido_id) {
        List<OrdenPlatoEntity> ordenDishEntities= ordenPlatoRepository.findByPedido_Id(pedido_id);
        if(ordenDishEntities.isEmpty()){
            throw new NoDataFoundException();
        }
        return OrdenPlatoEntityMapper.toOrderPlatoModelList(ordenDishEntities);
    }

    @Override
    public Orden getOrdenById(Long id) {
        Optional<OrdenEntity> ordenEntityOptional = ordenRepository.findById(id);
        OrdenEntity ordenEntity= ordenEntityOptional.orElse(null);
        return ordenEntityMapper.toOrden(ordenEntity);
    }

    @Override
    public Boolean existsByIdAndEstado(Long id, String estado) {
        return ordenRepository.existsByIdAndEstado(id, estado);
    }

}
