package com.pragma.powerup.domain.model;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



public class Orden {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Id
   // @Column(name = "pedido_id", nullable = false)
    private Long id;

   // @Column(name = "cliente_id", nullable = false)
    private Long idCliente;
   // @Column(name = "fecha", nullable = false)
    private Date fecha;
   // @Column(name = "estado")
    private String estado;
   // @ManyToOne
   // @JoinColumn(name = "chef_id")
    private RestaurantEmployee chef;
    //@ManyToOne
   // @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurant restaurante;

    public Orden() {
    }

    public Orden(Long id, Long idCliente, Date fecha, String estado, RestaurantEmployee chef, Restaurant restaurante) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
        this.chef = chef;
        this.restaurante = restaurante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public RestaurantEmployee getChef() {
        return chef;
    }

    public void setChef(RestaurantEmployee chef) {
        this.chef = chef;
    }

    public Restaurant getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurant restaurante) {
        this.restaurante = restaurante;
    }
}
