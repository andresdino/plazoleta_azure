package com.pragma.powerup.infrastructure.input.rest;


import com.pragma.powerup.application.dto.request.CategoriaRequestDTO;
import com.pragma.powerup.application.dto.response.CategoriaResponseDTO;
import com.pragma.powerup.application.handler.ICategoriaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
@RequiredArgsConstructor
public class CategoriaRestController {

    private final ICategoriaHandler categoriaHandler;

    @PostMapping("/save")
    public ResponseEntity<Void> saveCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDto){
        categoriaHandler.saveCategoria(categoriaRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias(){
        return ResponseEntity.ok(categoriaHandler.getAllCategoria());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getCategoriaById(@PathVariable(value = "id") Long rolId){
        return ResponseEntity.ok(categoriaHandler.getCategoriaById(rolId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable(value = "id") Long categoriaId){
        categoriaHandler.deleteCategoriaById(categoriaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
