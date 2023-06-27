package com.pragma.powerup.application.handler.impl;


import com.pragma.powerup.application.dto.request.CategoriaRequestDTO;
import com.pragma.powerup.application.dto.response.CategoriaResponseDTO;
import com.pragma.powerup.application.handler.ICategoriaHandler;
import com.pragma.powerup.application.mapper.request.ICategoriaRequestMapper;
import com.pragma.powerup.application.mapper.response.ICategoriaResponseMapper;
import com.pragma.powerup.domain.api.ICategoriaServicePort;
import com.pragma.powerup.domain.model.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional

public class CategoriaHandler implements ICategoriaHandler {
    private final ICategoriaServicePort categoriaServicePort;
    private final ICategoriaResponseMapper categoriaResponseMapper;
    private final ICategoriaRequestMapper categoriaRequestMapper;

    public CategoriaHandler(ICategoriaServicePort categoriaServicePort, ICategoriaResponseMapper categoriaResponseMapper, ICategoriaRequestMapper categoriaRequestMapper) {
        this.categoriaServicePort = categoriaServicePort;
        this.categoriaResponseMapper = categoriaResponseMapper;
        this.categoriaRequestMapper = categoriaRequestMapper;
    }

    @Override
    public void saveCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaRequestMapper.toCategoria(categoriaRequestDTO);
        categoriaServicePort.saveCateoria(categoria);
    }

    @Override
    public CategoriaResponseDTO getCategoriaById(Long id) {
        CategoriaResponseDTO categoriaResponseDTO = categoriaResponseMapper.toResponse(categoriaServicePort.getCateoriaById(id));
        return categoriaResponseDTO;
    }

    @Override
    public List<CategoriaResponseDTO> getAllCategoria() {
        return categoriaResponseMapper.toResponseList(categoriaServicePort.getAllCategoria());
    }

    @Override
    public void deleteCategoriaById(Long id) {
        categoriaServicePort.deleteCategoriaById(id);
    }
}
