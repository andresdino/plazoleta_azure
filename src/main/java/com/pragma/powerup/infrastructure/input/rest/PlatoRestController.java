package com.pragma.powerup.infrastructure.input.rest;


import com.pragma.powerup.application.dto.request.PlatoRequestDTO;
import com.pragma.powerup.application.dto.request.PlatoRequestPutDTO;
import com.pragma.powerup.application.dto.response.PlatoResponseDTO;
import com.pragma.powerup.application.handler.IPlatoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plato")
@RequiredArgsConstructor
public class PlatoRestController {

    private final IPlatoHandler platoHandler;


    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content)
    })
    @PostMapping("/save")
    public ResponseEntity<Void> savePlato(@Valid @RequestBody PlatoRequestDTO platoRequestDTO){
        platoHandler.savePlato(platoRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Update dish by Id, RequestBody price and description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish updated",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlatoRequestDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlatoRequestDTO> PutPlato (@PathVariable (value = "id") Long platoId, @Valid @RequestBody PlatoRequestPutDTO platoRequestPutDTO) {
        platoHandler.putPlato(platoId ,platoRequestPutDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Enable or disable dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish enable/disable",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlatoRequestDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @PutMapping("/{id}/activate/{enableDisable}")
    public ResponseEntity<PlatoRequestDTO> putEnableDisablePlato(@PathVariable (value = "id") Long platoId, @PathVariable(value = "enableDisable") Long enableDisable){
        platoHandler.putEnableDiseablePlato(platoId, enableDisable);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Get all dishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlatoResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/get")
    public ResponseEntity<List<PlatoResponseDTO>> getAllPlato(){
        return ResponseEntity.ok(platoHandler.getAllPlatos());
    }


    @Operation(summary = "Get all dishes by restaurant")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "All dishes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PlatoResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/restaurant/{idRestaurante}/page/{page}/size/{size}")
    public ResponseEntity<List<PlatoResponseDTO>> getAllPlatoByRestaurant(@PathVariable(value = "idRestaurante") Long idRestaurante,@PathVariable(value = "page") Integer page, @PathVariable(value = "size")Integer size){
        return ResponseEntity.ok(platoHandler.findAllByRestauranteId(idRestaurante, page,size));
    }


    @Operation(summary = "Get dish by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlatoResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Dish no found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<PlatoResponseDTO> getPlatoId(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(platoHandler.getPlatoById(id));
    }


    @Operation(summary = "Detele a dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatoById(@PathVariable(value="id") Long platoId){
        platoHandler.deletePlatoById(platoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
