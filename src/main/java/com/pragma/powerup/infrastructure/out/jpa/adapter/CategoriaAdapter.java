package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.spi.persistence.ICategoriaPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICategoriaEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoriaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoriaAdapter implements ICategoriaPersistencePort {


    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;
    @Override
    public Categoria saveCategoria(Categoria categoria) {
        CategoriaEntity categoriaEntity = categoriaRepository.save(categoriaEntityMapper.toEntity(categoria));
        return categoriaEntityMapper.toCategoriaModel(categoriaEntity);
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        Optional<CategoriaEntity> optionalCategoriaEntity = categoriaRepository.findById(id);
        CategoriaEntity categoriaEntity = optionalCategoriaEntity.orElse(null);
        return categoriaEntityMapper.toCategoriaModel(categoriaEntity);
    }

    @Override
    public List<Categoria> getAllCategoria() {
        List<CategoriaEntity> categoriaEntityList = categoriaRepository.findAll();
        if(categoriaEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return categoriaEntityMapper.toCategoriaModelList(categoriaEntityList);
    }

    @Override
    public void deleteCategoriaById(Long id) {
            categoriaRepository.deleteById(id);
    }
}
