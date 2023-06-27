package com.pragma.powerup.infrastructure.out.jpa.entity;


import com.pragma.powerup.domain.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "platos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlatoEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "plato_id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private String precio;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "url_imagen", nullable = false)
    private String urlImagen;

    @Column(name = "activo")
    private Boolean activo;


    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="resturante_id")
    private RestaurantEntity restaurantId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="category_id")
    private CategoriaEntity categoriaId;


}
