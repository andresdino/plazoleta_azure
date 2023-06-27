package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrdenPlatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrdenPlatoRepository extends JpaRepository<OrdenPlatoEntity, Long> {

    List<OrdenPlatoEntity> findByPedido_Id(Long pedido_id);
}
