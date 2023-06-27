package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdenEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pedido_id", nullable = false)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long idCliente;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private RestaurantEmployeeEntity chef;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private RestaurantEntity restaurante;

}
