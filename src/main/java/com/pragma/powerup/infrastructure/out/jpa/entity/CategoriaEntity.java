package com.pragma.powerup.infrastructure.out.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",nullable = false)
    private Long id;


    @Column(name = "nombre")
    @NotBlank
    private  String nombre;

    @Column(name = "descripcion")
    @NotBlank
    private String descripcion;
}
