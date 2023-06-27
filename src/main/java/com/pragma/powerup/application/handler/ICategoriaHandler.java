package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.CategoriaRequestDTO;
import com.pragma.powerup.application.dto.response.CategoriaResponseDTO;

import java.util.List;

public interface ICategoriaHandler {

    void saveCategoria(CategoriaRequestDTO categoriaRequestDTO);

    CategoriaResponseDTO getCategoriaById(Long id);

    List<CategoriaResponseDTO> getAllCategoria();

    void deleteCategoriaById(Long id);


}
