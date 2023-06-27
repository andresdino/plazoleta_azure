package com.pragma.powerup.domain.model;

public class Plato {

    private Long id;
    private String nombre;
    private String precio;
    private String descripcion;
    private String urlImagen;
    private Boolean activo;
    private Restaurant restaurantId;
    private Categoria categoriaId;

    public Plato() {
    }

    public Plato(Long id, String nombre, String precio, String descripcion, String urlImagen, Boolean activo, Restaurant restaurantId, Categoria categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.activo = activo;
        this.restaurantId = restaurantId;
        this.categoriaId = categoriaId;
    }

    public Plato( Long id, String precio, String descripcion) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestauranteId(Restaurant restauranteId) {
        this.restaurantId = restauranteId;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }
}
