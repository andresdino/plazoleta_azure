package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ICategoriaServicePort;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.spi.persistence.ICategoriaPersistencePort;

import java.util.List;

public class CategoriaUseCase implements ICategoriaServicePort {

    private final ICategoriaPersistencePort categoriaPersistencePort;

    public CategoriaUseCase(ICategoriaPersistencePort categoriaPersistencePort) {
        this.categoriaPersistencePort = categoriaPersistencePort;
    }


    @Override
    public void saveCateoria(Categoria categoria) {
        categoriaPersistencePort.saveCategoria(categoria);
    }

    @Override
    public Categoria getCateoriaById(Long id) {
        return categoriaPersistencePort.getCategoriaById(id);
    }

    @Override
    public List<Categoria> getAllCategoria() {

        return categoriaPersistencePort.getAllCategoria();
    }

    @Override
    public void deleteCategoriaById(Long id) {
        categoriaPersistencePort.deleteCategoriaById(id);
    }
}
