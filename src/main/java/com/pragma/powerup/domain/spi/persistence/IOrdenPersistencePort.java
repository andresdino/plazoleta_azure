package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.Orden;
import com.pragma.powerup.domain.model.OrdenPlato;

import java.util.List;

public interface IOrdenPersistencePort {
    Orden saveOrden(Orden orden);

    void saveOrdenPlato(List<OrdenPlato> ordenPlatos);


    List<Orden> getAllOrdenesWithPagination(Integer page, Integer size, Long restauranteId, String estado);

    List<OrdenPlato> getAllOrdenessByPedido(Long pedido_id);

    Orden getOrdenById(Long id);

    Boolean existsByIdAndEstado(Long id, String estado);
}
