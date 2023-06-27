package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.Categoria;

import java.util.List;

public interface ICategoriaPersistencePort {

    Categoria saveCategoria(Categoria categoria);

    Categoria getCategoriaById(Long id);

    List<Categoria> getAllCategoria();

    void deleteCategoriaById(Long id);
}
