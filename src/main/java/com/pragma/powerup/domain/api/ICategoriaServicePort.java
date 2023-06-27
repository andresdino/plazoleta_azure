package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;



public interface ICategoriaServicePort {

    void saveCateoria(Categoria categoria);

    Categoria getCateoriaById(Long id);

    List<Categoria> getAllCategoria();

    void deleteCategoriaById(Long id);
}
