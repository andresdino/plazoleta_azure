package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrdenEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenRepository extends JpaRepository<OrdenEntity, Long> {
    boolean existsByIdClienteAndEstado(Long id, String estado);


    Page<OrdenEntity> findByRestaurante_idAndEstado(Long id, String estado, Pageable pageable);

    Boolean existsByIdAndEstado(Long id, String estado);

}