package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.Orden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "pedidos_platos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdenPlatoEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pedido_plato_id", nullable = false)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private OrdenEntity pedido;

    @ManyToOne
    @JoinColumn(name = "plato_id", nullable = false)
    private PlatoEntity plato;

    @Column(name = "cantidad", nullable = false)
    private String cantidad;
}
